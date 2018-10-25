package app.data.refresh;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface RefreshConstants {

	public static final String FILE_NAME = "vm-details.json";
	public static final String BASE_PATH = "buckets";
	public static final String SERVICE_CODE = "AmazonEC2";
	public static final String OFFERS_URL_TEMPLATE = "https://pricing.us-east-1.amazonaws.com/offers/v1.0/aws/%s/current/%s/index.csv";
	public static final String BUCKET_NAME_FORMAT = "cadencecloud-prod-e-mirrors-%s";
	public static final Integer LINES_TO_SKIP = 5;
	public static final List<String> REGIONS = Arrays.asList("us-west-2", "us-east-1", "eu-west-1", "eu-central-1", "ap-northeast-1", "ap-south-1");
	//public static final List<String> REGIONS = Arrays.asList("us-west-2");
	public static final Map<String,String> FILTERS = Map.of("termType", "OnDemand", "operatingSystem", "Linux", "tenancy", "Shared" /*"usage", "USW2-BoxUsage:m4.large",*/ 
			/*"capacity", "Used"/*, "operation", "RunInstances", */,"software", "NA");

}
