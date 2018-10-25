package app;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MigratorApplication {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ExecutorService executor = Executors.newWorkStealingPool(3);
		Future<List<CloudMachine>> csvInputJob = executor.submit(new CSVInput(args[0]));
		Future<List<MachineInfo>> jsonInputJob = executor.submit(new JSONInput(args[1]));
		List<CloudMachine> csvData = csvInputJob.get();
		List<MachineInfo> jsonData = jsonInputJob.get();
		List<MachineInfo> updatedData = CSV2JSONMapper.map(csvData, jsonData);
		Future<String> jsonOutputJob = executor.submit(new JSONOutput(updatedData));
		String filePath = jsonOutputJob.get();
		System.out.println(filePath);
		executor.shutdown();
	}

}
