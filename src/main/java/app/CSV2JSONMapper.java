package app;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import com.sangupta.gather.Gather;

public class CSV2JSONMapper {
	
		public static List<MachineInfo> map(List<CloudMachine> cmList, List<MachineInfo> vmList) throws FileNotFoundException {
		/*Gather query = Gather.where("accountName").is("aws-uc2").and("os").is("Linux")
				.and("region").is("us-west-2").and("pricingModel").is("od").and("isVPC").is(true);*/
		Gather query = Gather.where("accountName").is("aws-uc2")/*.and("isVPC").is(true)*/.and("os").is("Linux")
				/*.and("pricingModel").is("od").and("region").is("us-west-2")*/;
		List<CloudMachine> filteredCloudMachines = query.find(cmList);
		//dumpFiltered(filteredCloudMachines);
		Collections.sort(filteredCloudMachines);
		for(MachineInfo mi : vmList) {
			CloudMachine key = new CloudMachine(mi.getName());
			int index = Collections.binarySearch(filteredCloudMachines, key);
			Double pricePerUnit = 0.0d;
			if(index >= 0) {
				key = filteredCloudMachines.get(index);
				pricePerUnit = key.getPrice();
			} 
			mi.setPricePerUnit(pricePerUnit);
		}
		return vmList;
		}
		
		private static void dumpFiltered(List<CloudMachine> filtered) throws FileNotFoundException {
			String fileName = "out_" + System.currentTimeMillis() + ".csv";
			PrintStream out = new PrintStream(new BufferedOutputStream(new FileOutputStream(fileName)), true);
			System.setOut(out);
			for(CloudMachine cm : filtered) {
				System.out.println(cm);
			}
			System.setOut(System.out);
		}
	
}
