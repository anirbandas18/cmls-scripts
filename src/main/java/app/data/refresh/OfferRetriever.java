package app.data.refresh;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.sangupta.gather.Gather;

import app.MachineInfo;


public class OfferRetriever implements Callable<String>, RefreshConstants {
	
	private String regionName;
	private String threadName;
	private Gather query;

	public OfferRetriever(String regionName) {
		Thread.currentThread().setName("Thread: " + regionName);
		this.regionName = regionName;
		this.threadName = Thread.currentThread().getName() + ": ";
		int c = 0;
		for(String key : FILTERS.keySet()) {
			String value = FILTERS.get(key);
			if(c == 0) {
				this.query = Gather.where(key).is(value);
			} else {
				this.query = query.and(key).is(value);
			}
			c++;
		}
		//this.query = Gather.where("termType").is("OnDemand").and("operatingSystem").is("Linux");
	}

	private Reader getOfferData() throws IOException {
		String offersEndpoint = String.format(OFFERS_URL_TEMPLATE, SERVICE_CODE, regionName);
		URL offersURL = new URL(offersEndpoint);
		System.out.println(threadName + " opened connection: " + offersURL);
		URLConnection offersConn = offersURL.openConnection();
		InputStream rawOfferData = offersConn.getInputStream();
		Reader offerDataReader = new InputStreamReader(rawOfferData);
		System.out.println(threadName + " retrieved data");
		return offerDataReader;
	}
	
	private List<Offer> parseOfferData(Reader offerDataReader) {
		System.out.println(threadName + " parsing data");
		CsvToBeanBuilder<Offer> builder = new CsvToBeanBuilder<>(offerDataReader);
		builder = builder.withType(Offer.class);
		builder = builder.withSkipLines(LINES_TO_SKIP);
		CsvToBean<Offer> csvParser = builder.build();
		List<Offer> offerList = csvParser.parse();
		return offerList;
	}
	
	@Override
	public String call() throws Exception {
		Long start = System.currentTimeMillis();
		Reader offerDataReader = getOfferData();
		List<Offer> offerList = parseOfferData(offerDataReader);
		System.out.println(threadName + " unfiltered data count: " + offerList.size());
		System.out.println(threadName + " filtering data on: " + query);
		List<Offer> filteredOffers = query.find(offerList);
		System.out.println(threadName + " filtered data count: " + filteredOffers.size());
		Collections.sort(filteredOffers);
		List<MachineInfo> machineList = new ArrayList<>();
		for(Offer offer : filteredOffers) {
			MachineInfo info = convert(offer);
			machineList.add(info);
		}
		persistFile(machineList);
		Long end  = System.currentTimeMillis();
		return threadName + " data refresh completed in " + (end - start) + " ms";
	}

	private MachineInfo convert(Offer offer) {
		//System.out.println(threadName + " converting data from: " + offer);
		MachineInfo info = new MachineInfo();
		info.setId(offer.getSku());
		info.setCoreCount(offer.getVcpu());
		info.setName(offer.getInstanceType());
		info.setPricePerUnit(offer.getPricePerUnit());
		String memoryValue = offer.getMemory().split("\\s")[0];
		memoryValue = memoryValue.replaceAll("\\,", "");
		info.setMemory(new BigDecimal(memoryValue.equalsIgnoreCase("NA") ? "0" : memoryValue));
		//System.out.println(threadName + " converted data to: " + info);
		return info;
	}
	
	private void persistFile(List<MachineInfo> vmList) throws IOException {
		Path bucketName = Paths.get(BASE_PATH, String.format(BUCKET_NAME_FORMAT, regionName));
		if(!Files.exists(bucketName)) {
			Files.createDirectory(bucketName);
		}
		Path filePath = Paths.get(bucketName.toString(), FILE_NAME);
		Writer jsonWriter = new FileWriter(filePath.toString());
		ObjectMapper jsonMapper = new ObjectMapper();
		jsonMapper.writerWithDefaultPrettyPrinter().writeValue(jsonWriter, vmList);
		jsonWriter.close();
		System.out.println(threadName + " saved data to: " + filePath);
	}
	
}
