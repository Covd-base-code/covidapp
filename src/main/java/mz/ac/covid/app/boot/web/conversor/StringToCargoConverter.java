package mz.ac.covid.app.boot.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import mz.ac.covid.app.boot.domain.Cargo;
import mz.ac.covid.app.boot.service.CargoService;

@Component
public class StringToCargoConverter implements Converter<String, Cargo> {

    @Autowired
    private CargoService service;

    @Override
    public Cargo convert(String source) {
        if (source.isEmpty()) {

            return null;
        }
        Long id = Long.valueOf(source);
        return service.pesquisarPorId(id);
    }

}
