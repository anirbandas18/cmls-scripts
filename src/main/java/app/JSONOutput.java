package app;

import java.io.FileWriter;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONOutput implements Callable<String> {
	
	private List<MachineInfo> vmList;
	private String filePath;
	
	public JSONOutput(List<MachineInfo> vmList) {
		this.vmList = vmList;
		this.filePath = "vm-details.json";
	}

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		for(MachineInfo mi : vmList) {
			System.out.println(mi);
		}
		Writer jsonWriter = new FileWriter(filePath);
		ObjectMapper jsonMapper = new ObjectMapper();
		jsonMapper.writerWithDefaultPrettyPrinter().writeValue(jsonWriter, vmList);
		jsonWriter.close();
		return filePath;
	}

}
