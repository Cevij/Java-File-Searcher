import java.io.File;
import java.util.ArrayList;
import java.util.List;

class FileCollector{

	private void DirectoryChecker(File file, String searchCriteria, List<String> matchingCriteriaList){
		if(file.listFiles() == null) {
			return;
		}
		
		for(final File fileEntry: file.listFiles()){
	        if(fileEntry.isDirectory()){
	            DirectoryChecker(fileEntry, searchCriteria, matchingCriteriaList);
	            continue;
	        }
	        
	        String fileNameUpperCase = fileEntry.getName().toUpperCase();
	        String searchCriteriaUpperCase = searchCriteria.toUpperCase();
	        
	        if(fileNameUpperCase.contains(searchCriteriaUpperCase)){
	        	matchingCriteriaList.add(fileEntry.getName());
            }
	        
	    }
	}

	public List<String> GetFilesFromDir(String dir, String searchCriteria){
	    final File folder = new File(dir);
		
	    List<String> matchingSearchCriteria = new ArrayList<String>();
	    
	    DirectoryChecker(folder, searchCriteria, matchingSearchCriteria);
	    
	    return matchingSearchCriteria;
	}
	
	private void DirectoryChecker(File file, long fileSize, String searchCriteria, List<String> matchingCriteriaList){
		if(file.listFiles() == null) {
			return;
		}
		
		for(final File fileEntry: file.listFiles()){
	        if(fileEntry.isDirectory()){
	            DirectoryChecker(fileEntry, fileSize, searchCriteria, matchingCriteriaList);
	            continue;
	        }
	        
	        String fileNameUpperCase = fileEntry.getName().toUpperCase();
	        String searchCriteriaUpperCase = searchCriteria.toUpperCase();
	        
	        if(fileNameUpperCase.contains(searchCriteriaUpperCase) &&  fileSize >= fileEntry.length() ){
	        	String sizeToString = String.valueOf(fileEntry.length());
	        	matchingCriteriaList.add(fileEntry.getName()+" File Size: "+ sizeToString+" Bytes");
            }
	        
	    }
	}

	public List<String> GetFilesFromDir(String dir, long fileSize, String searchCriteria){
		
	    final File folder = new File(dir);
	    List<String> matchingSearchCriteria = new ArrayList<String>();
	    
	    DirectoryChecker(folder, fileSize, searchCriteria, matchingSearchCriteria);
	    
	    return matchingSearchCriteria;
	}
}


public class FileCollectorDriver {

	public static void main(String[] args) {
		// dir format C:\\Users\\**\\Desktop\\**
		final String dir = "C:\\Users";
		final String nameToSearch = "";
		final long fileSize = 120;
		
		FileCollector searcher = new FileCollector();
		
		List<String> searchByFileName = searcher.GetFilesFromDir(dir, nameToSearch);
		List<String> searchByFileNameAndSize = searcher.GetFilesFromDir(dir, fileSize, nameToSearch);
		
		System.out.println("---------Search by File Name----------");
		for(String fileName : searchByFileName){
			System.out.printf("File name: %s %n", fileName);
		}
		
		System.out.printf("Number of Files: %d %n", searchByFileName.size());
		
		System.out.println();
		System.out.println("---------Search by File Name and File Size----------");
		
		for(String fileName : searchByFileNameAndSize){
			System.out.printf("File name: %s %n", fileName);
		}
		
		System.out.printf("Number of Files: %d %n", searchByFileNameAndSize.size());
	}

}
