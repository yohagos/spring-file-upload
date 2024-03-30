package com.spring.fileupload;

import com.spring.fileupload.upload.StorageProperties;
import com.spring.fileupload.upload.StorageService;
import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}

	@Configuration
	public class WebMvcConfig {

		@Bean
		public MultipartConfigElement multipartConfigElement() {
			MultipartConfigElement multipartConfigElement = new MultipartConfigElement("/data", 1024 * 1024 * 10, 1024 * 1024 * 100, 1024 * 1024 * 100);
			return multipartConfigElement;
		}
	}

}
