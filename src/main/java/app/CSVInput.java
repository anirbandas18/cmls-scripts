package app;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.Callable;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVInput implements Callable<List<CloudMachine>> {
	
	private String filePath;
	
	public CSVInput(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public List<CloudMachine> call() throws Exception {
		// TODO Auto-generated method stub
		Reader csvReader = new FileReader(filePath);
		CsvToBeanBuilder<CloudMachine> builder = new CsvToBeanBuilder<>(csvReader);
		builder = builder.withType(CloudMachine.class);
		CsvToBean<CloudMachine> csvParser = builder.build();
		List<CloudMachine> csvData = csvParser.parse();
		//for(CloudMachine cm : csvData) {
			//System.out.println(Thread.currentThread().getName() + ": " + cm);
		//}
		csvReader.close();
		return csvData;
	}

}
