package proyectopdf.proyectopdf.Service.Upload;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImpUpload implements IUpload{
    private final Path rootFolder = Paths.get("uploads");

	@Override
	public String save(MultipartFile file) throws Exception {
		String nombre = Math.random()+"_"+file.getOriginalFilename();
		Files.copy(file.getInputStream(), this.rootFolder.resolve(nombre));
		return nombre;
	}

	@Override
	public Resource load(String name) throws Exception {
		Path file = rootFolder.resolve(name);
		Resource resource = new UrlResource(file.toUri());
		return resource;
	}

	@Override
	public String save(List<MultipartFile> files) throws Exception {
		String nombre = "";
		for (MultipartFile file : files) {
			nombre = this.save(file);
		}
	return nombre;
	}

	@Override
	public Stream<Path> loadAll() throws Exception {
		return Files.walk(rootFolder, 1).filter(path -> !path.equals(rootFolder)).map(rootFolder::relativize);
	}

	@Override
	public String edit(MultipartFile file) throws Exception {
		
		String nombre = "signed_"+file.getOriginalFilename();
		Files.copy(file.getInputStream(), this.rootFolder.resolve(nombre));
		return nombre;
	}

	
}
