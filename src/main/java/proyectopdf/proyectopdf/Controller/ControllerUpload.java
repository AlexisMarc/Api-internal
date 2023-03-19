package proyectopdf.proyectopdf.Controller;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import proyectopdf.proyectopdf.Messages.Upload.Response;
import proyectopdf.proyectopdf.Service.Upload.ImpUpload;

@RestController
@RequestMapping("/files")
public class ControllerUpload {
    @Autowired
	private ImpUpload IUpload;

	@PostMapping("/upload")
	public ResponseEntity<Response> uploadFiles(@RequestParam("files") List<MultipartFile> files) throws Exception {
		String nombre = IUpload.save(files);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(nombre));
	}

	@GetMapping("/{filename:.+}")
	public ResponseEntity<Resource> getFile(@PathVariable String filename) throws Exception {
		Resource resource = IUpload.load(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<File>> getAllFiles() throws Exception {
		List<File> files = IUpload.loadAll().map(path -> {
			String filename = path.getFileName().toString();
			String url = MvcUriComponentsBuilder.fromMethodName(ControllerUpload.class, "getFile", path.getFileName().toString()).build().toString();
			
			return new File(filename, url);
		}).collect(Collectors.toList());
		
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@PostMapping("/firm")
	public ResponseEntity<Response> uploadFilesForm(@RequestParam("file") MultipartFile file) throws Exception {
		String nombre = IUpload.firm(file);
		return ResponseEntity.status(HttpStatus.OK)
				.body(new Response(nombre));
	}
}
