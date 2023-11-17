package com.example.ProyectoFinal_EmpresaGloria.controller;

import com.example.ProyectoFinal_EmpresaGloria.model.Producto;
import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;
import com.example.ProyectoFinal_EmpresaGloria.service.IProductoService;
import com.example.ProyectoFinal_EmpresaGloria.service.IUsuarioService;
import com.example.ProyectoFinal_EmpresaGloria.service.UploadFileService;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller //Es un componente de Spring capaz de recibir peticiones http y responderlas.
@RequestMapping("/productos") //Asignamos el mappeo general conrrespondiente a cada metodo
public class ProductoController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class); //Registrador de 
    //datos el cual nos ayudara a mostrar los datos en consola en tiempo real

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IProductoService productoService;

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IUsuarioService usuarioService;

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private UploadFileService upload;

    @GetMapping("")
    //Metodo show que nos ayudara a listar la informacion de los productos
    public String show(Model model) {
        //Dentro del modelo listamos todos los datos de productos directamente de la base de datos
        model.addAttribute("productos", productoService.findAll());
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "productos/show";
    }

    @GetMapping("/create")
    //Metodo create que nos ayudara a crear los productos
    public String create() {
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        //Capturamos el id del usuario de la session
        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        //Añadimos la session del usuario a la instancia producto
        producto.setUsuario(u);

        //Validacion para la asignacion de la imagen
        if (producto.getId() == null) { // cuando se crea un producto
            //Asignamos la imagen segun su ruta
            String nombreImagen = upload.saveImage(file);
            //añadimos la imagen al producto
            producto.setImagen(nombreImagen);
        } else {

        }
        //Guardamos la informacion del producto en la base de datos
        productoService.save(producto);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "redirect:/productos";
    }

    @GetMapping("/edit/{id}")
    //Metodo para editar los datos de los productos
    public String edit(@PathVariable Integer id, Model model) {
        //Declaramos la instancia producto de la clase Producto
        Producto producto = new Producto();
        //Capturamos en id del producto de la base de datos
        Optional<Producto> optionalProducto = productoService.get(id);
        //Igualamos a producto para poder llamar los datos directamente de la base de datos
        producto = optionalProducto.get();
        //Mostramos la informacion del producto en consola
        LOGGER.info("Producto buscado: {}", producto);
        //Añadimos la informacion del producto al modelo
        model.addAttribute("producto", producto);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "productos/edit";
    }

    @PostMapping("/update")
    //Metodo que nos ayudara a guardar la informacion del producto actualizado
    public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        Producto p = new Producto(); //Instanciamos a p de la clase Producto
        //a la instancia p se asignamos el id del producto
        p = productoService.get(producto.getId()).get();

        if (file.isEmpty()) { //Editamos el producto
            //Mostramos la informacion de la imagen
            producto.setImagen(p.getImagen());
        } else {//Caso contrario cuando se edita tambien la imagen			
            //Se eliminara cuando la imagen no se por defecto
            if (!p.getImagen().equals("default.jpg")) {
                //Guardamos la imagen
                upload.deleteImage(p.getImagen());
            }
            String nombreImagen = upload.saveImage(file); //Nueva ruta de la imagen
            producto.setImagen(nombreImagen); //Guardamos la nueva imagen asignada
        }
        //Añadimos los datos del usuario a la instancia producto de la clase Producto
        producto.setUsuario(p.getUsuario());
        //Guardamos el producto actualizado
        productoService.update(producto);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "redirect:/productos";
    }

    @GetMapping("/delete/{id}")
    //Metodo que eliminara un producto a traves de su id
    public String delete(@PathVariable Integer id) {
        Producto p = new Producto(); //Instanciamos a p de la clase Producto
        //a la instancia p se asignamos el id del producto
        p = productoService.get(id).get();

        //Se eliminara la ruta de la imagen y la imagen cuando no se la por defecto
        if (!p.getImagen().equals("default.jpg")) {
            upload.deleteImage(p.getImagen()); //Guardamos la imagen por defecto
        }

        productoService.delete(id); //Eliminamos el producto de la base de datos a traves de su id
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "redirect:/productos";
    }

    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    //Metodo que nos ayudara a buscar la informacion del producto
    public String buscar(@RequestParam("desc") String desc, Model model) {
        //Hacemos el llamado de listarProductoBuscar el cual contendra el query que hara el filtrado
        //de la informacion a traves del nombre del producto
        List<Producto> productos = productoService.listarProductoBuscar(desc);
        //Agregamos al modelo la informacion del producto a buscar
        model.addAttribute("productos", productos);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "productos/show";
    }

}
