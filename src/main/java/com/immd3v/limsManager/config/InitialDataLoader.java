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
    @PostConstruct
    private void loadData() {
        containerDataCreator("1", 4058.19);
        containerDataCreator("2", 6944.08);
        containerDataCreator("3", 2030.89);
        containerDataCreator("4", 2086.08);
        containerDataCreator("5", 4296.34);
        containerDataCreator("6", 9040.83);
        containerDataCreator("7", 4735.53);
        containerDataCreator("8", 4015.91);
        containerDataCreator("9", 6326.15);
        containerDataCreator("10", 6683.21);
        containerDataCreator("11", 6443.50);
        containerDataCreator("12", 4166.60);
        containerDataCreator("13", 4863.74);
        containerDataCreator("14", 2936.72);
        containerDataCreator("15", 2943.62);
        containerDataCreator("16", 7414.31);
        containerDataCreator("17", 6345.60);
        containerDataCreator("18", 6595.82);
        containerDataCreator("19", 9474.22);
        containerDataCreator("20", 5416.56);
    }
}
