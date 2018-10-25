package app.data.refresh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class RefresherApplication implements RefreshConstants {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		//if(args.length != 0) {
		args = args.length == 0 ? REGIONS.toArray(new String[REGIONS.size()]) : args;
		int nThreads = args.length;
			Long start = System.currentTimeMillis();
			Path basePath = Paths.get(BASE_PATH);
			if(!Files.exists(basePath)) {
				Files.createDirectory(basePath);
			}
			ExecutorService executor = Executors.newFixedThreadPool(nThreads);
			List<Future<String>> jobList = new ArrayList<>();
			for(String region : args) {
				Callable<String> refreshWork = new OfferRetriever(region);
				Future<String> refreshJob = executor.submit(refreshWork);
				jobList.add(refreshJob);
			}
			for(Future<String> refreshJob : jobList) {
				String output = refreshJob.get();
				System.out.println(output);
			}
			executor.shutdown();
			Long end  = System.currentTimeMillis();
			System.out.println(Thread.currentThread().getName() + " executed for " + (end - start) + " ms");
		/*} else {
			System.err.println("Expected at least one argument which is path to a file containing AWS region codes");
		}*/
	}

}
