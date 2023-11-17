package com.example.ProyectoFinal_EmpresaGloria.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service //Nos sirve para crear una clase servicio el cual conecta a varios repositorios
public class UploadFileService {

    private String folder = "images//"; //variable folder de tipo cadena el cual contendra la ruta
    //donde se encontraran las imagenes

    //Metodo que nos ayudara a localizar la imagen y poder hacer la carga en los productos
    public String saveImage(MultipartFile file) throws IOException {
        //MultipartFile documento de varios archivos
        if (!file.isEmpty()) { //Si el tamaño de la cadena no esta vacio
            byte[] bytes = file.getBytes(); //guardamos en memoria el tamaño de bytes 
            //del archivo
            Path path = Paths.get(folder + file.getOriginalFilename()); //A traves del path
            //configuramos la url donde se situa las imagenes
            Files.write(path, bytes); //escribimos en el archivo, la ruta donde se encuentra
            return file.getOriginalFilename();  //devolvemos el nombre del archivo
        }
        return "default.jpg"; //en caso no haya ninguna imagen retornara una imagen por default
    }

    //Metodo para eliminar la imagen a traves de su ruta
    public void deleteImage(String nombre) {
        String ruta = "images//"; //creamos una variable ruta de tipo cadena el cual contendra
        //el directorio donde se localizaran las imagenes
        File file = new File(ruta + nombre); //concatenamos la ruta + el nombre dela imagen
        file.delete(); //y si la imagen se encuentra en el directorio, sera eliminada
    }

}
