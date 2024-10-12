package com.immd3v.limsManager.config;

import com.immd3v.limsManager.entity.Container;
import com.immd3v.limsManager.repository.ContainerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitialDataLoader {
    @Autowired
    private ContainerRepository containerRepository;

    private void containerDataCreator(String name, Double capacity) {
        if (!containerRepository.existsByName(name)) {
            Container containerLoader = new Container();
            containerLoader.setName(name);
            containerLoader.setCapacity(capacity);
            containerRepository.save(containerLoader);
        }
    }
//    @PostConstruct
//    private void loadData() {
//        containerDataCreator("1", 4058.19);
//        containerDataCreator("2", 6944.08);
//        containerDataCreator("3", 2030.89);
//        containerDataCreator("4", 2086.08);
//        containerDataCreator("5", 4296.34);
//        containerDataCreator("6", 9040.83);
//        containerDataCreator("7", 4735.53);
//        containerDataCreator("8", 4015.91);
//        containerDataCreator("9", 6326.15);
//    }
}
