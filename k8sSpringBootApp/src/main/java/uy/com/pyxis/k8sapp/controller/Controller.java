package uy.com.pyxis.k8sapp.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.com.pyxis.k8sapp.config.Properties;

@RestController
public class Controller {

  private final Properties properties;
  private final String FILES_PATH = "/volume-mount-path";
  @Value("${HOSTNAME:There is not information of HOSTNAME}")
  private String hostName;
  @Value("${BAR:There is not information of environment variable BAR}")
  private String bar;
  @Value("${PASSWORD:There is not information of secret variable PASSWORD}")
  private String password;

  public Controller(Properties properties) {
    this.properties = properties;
  }

  @GetMapping(value = "/foo", produces = {MediaType.TEXT_PLAIN_VALUE})
  public String foo() {
    return hostName;
  }

  @GetMapping(value = "/bar", produces = {MediaType.TEXT_PLAIN_VALUE})
  public String bar() {
    return bar;
  }

  @GetMapping(value = "/configmap", produces = {MediaType.TEXT_PLAIN_VALUE})
  public String configmap() {
    return properties.getVariable();
  }

  @GetMapping(value = "/password", produces = {MediaType.TEXT_PLAIN_VALUE})
  public String password() {
    return password;
  }

  @PutMapping(value = "/create/{file}", produces = {MediaType.TEXT_PLAIN_VALUE})
  public String createFile(@PathVariable String file) throws IOException {
    if (Files.exists(Path.of(FILES_PATH))) {
      var createdFile = new File("/volume-mount-path/" + file);
      var created = createdFile.createNewFile();
      return created ? "Created " + file : file + " already exists";
    } else {
      return "Directory of files does not exists.";
    }
  }

  @GetMapping(value = "/list", produces = {MediaType.TEXT_PLAIN_VALUE})
  public String listFile() {
    if (Files.exists(Path.of(FILES_PATH))) {
      return Stream.of(Objects.requireNonNull(new File("/volume-mount-path").listFiles()))
                   .filter(file -> !file.isDirectory())
                   .map(File::getName)
                   .collect(Collectors.toSet()).toString();
    } else {
      return "Directory of files does not exists.";
    }
  }
}
