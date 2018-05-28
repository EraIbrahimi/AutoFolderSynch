import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Client {
	public static final String LOCAL_DIR_PATH = "D:\\Fotografite\\Nuk_jon_ne_hard-disk\\Era\\FSHMN\\Viti3\\Sync";
	public static void main(String[] args) {
		String dirPath = "";
		System.out.println("Enter the directory to sync:");
		Scanner scan = new Scanner(System.in);
		dirPath = scan.nextLine();
		scan.close();
		ExecutorService executor = null;
		try{
			File dir = new File(dirPath);
			if(dir.exists() && dir.isDirectory()){
				executor = Executors.newCachedThreadPool();
				FileSyncManager manager = new FileSyncManager(dir, executor);
				executor.execute(manager);
			}else{
				System.out.println("Invalid directory. Wheel again!");
			}
		}catch(RuntimeException e){
			System.out.printf("It broke: %s\n", e.getMessage());
		}
	}
}
