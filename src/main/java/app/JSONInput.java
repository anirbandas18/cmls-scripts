package app;

import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.Callable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONInput implements Callable<List<MachineInfo>> {
	
	private String filePath;
	
	public JSONInput(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public List<MachineInfo> call() throws Exception {
		// TODO Auto-generated method stub
		Reader jsonReader = new FileReader(filePath);
		ObjectMapper jsonMapper = new ObjectMapper();
		List<MachineInfo> vmList = jsonMapper.readValue(jsonReader, new TypeReference<List<MachineInfo>>() { });
		/*for(MachineInfo mi : vmList) {
			System.out.println(mi);
		}*/
		jsonReader.close();
		return vmList;
	}

}
