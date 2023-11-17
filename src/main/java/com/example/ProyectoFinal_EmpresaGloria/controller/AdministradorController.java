package com.example.ProyectoFinal_EmpresaGloria.controller;

import com.example.ProyectoFinal_EmpresaGloria.model.Contacto;
import com.example.ProyectoFinal_EmpresaGloria.model.DetalleOrden;
import com.example.ProyectoFinal_EmpresaGloria.model.Orden;
import com.example.ProyectoFinal_EmpresaGloria.model.Producto;
import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;
import com.example.ProyectoFinal_EmpresaGloria.service.IContactoService;
import com.example.ProyectoFinal_EmpresaGloria.service.IDetalleOrdenService;
import com.example.ProyectoFinal_EmpresaGloria.service.IOrdenService;
import com.example.ProyectoFinal_EmpresaGloria.service.IProductoService;
import com.example.ProyectoFinal_EmpresaGloria.service.IUsuarioService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //Es un componente de Spring capaz de recibir peticiones http y responderlas.
@RequestMapping("/administrador") //Asignamos el mappeo general conrrespondiente a cada metodo
public class AdministradorController {

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IProductoService productoService;

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IUsuarioService usuarioService;

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IOrdenService ordensService;

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IContactoService contactoService;

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IDetalleOrdenService detalleService;

    private Logger logg = LoggerFactory.getLogger(AdministradorController.class); //El logger nos ayudara
    //a visualizar datos en consola

    @GetMapping("")
    //Metodo de tipo string el cual nos ayudara para el listado de productos
    public String home(Model model) {
        //Creamos una lista de productos el cual nos ayudara a listar toda la informacion de los productos
        //que haya en la base de datos
        List<Producto> productos = productoService.findAll();
        //Agregamos al modelo la instancia productos para poder llamarlo en un html
        model.addAttribute("productos", productos);
        //Retornamos la ruta donde se encontrara el html donde queremos que se muestre la informacion
        return "administrador/homeAdmin";
    }

    @GetMapping("/usuarios")
    //Metodo de tipo string el cual nos ayudara para el listado de usuarios
    public String usuarios(Model model) {
        //Dentro del modelo listamos mediante el usuarioService toda la informacion de la base de datos
        model.addAttribute("usuarios", usuarioService.findAll());
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    //Metodo de tipo string el cual nos ayudara para el listado de las ordenes
    public String ordenes(Model model) {
        //Dentro del modelo listamos mediante el ordensService toda la informacion de la base de datos
        model.addAttribute("ordenes", ordensService.findAll());
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "administrador/ordenes";
    }

    @GetMapping("/contactanos")
    //Metodo de tipo string el cual nos ayudara para el listado de las quejas o recomendaciones
    public String contactanos(Model model) {
        //Dentro del modelo listamos mediante el contactoService toda la informacion de la base de datos
        model.addAttribute("contactos", contactoService.findAll());
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "administrador/contactanos";
    }

    @GetMapping("/detalle/{id}")
    //Metodo de tipo string el cual nos ayudara para el listado de los detalles de la orden a traves de 
    //un parametro el cual sera el id
    public String detalle(Model model, @PathVariable Integer id) {
        logg.info("Id de la orden {}", id);
        //Dentro de la informacion de la base de datos capturas el id
        Orden orden = ordensService.findById(id).get();
        //Dentro del modelo listamos mediante el orden toda la informacion de los detalles de orden
        model.addAttribute("detalles", orden.getDetalle());
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "administrador/detalleorden";
    }

    //Metodo que nos ayuda en el filtrado de la informacion de los usuarios
    @RequestMapping(value = "/buscar", method = RequestMethod.POST)
    //El parametro de busqueda de informacion sera el email
    public String buscar(@RequestParam("desc") String desc, Model model) {
        //Dentro de la lista de la clase Usuario listaremos la listarUsuarioBuscar que estara
        //compuesta de un queryList<Usuario> usuarios = usuarioService.listarUsuarioBuscar(desc);
        List<Usuario> usuarios = usuarioService.listarUsuarioBuscar(desc);
        //Agregamos la instancia usuarios al modelo
        model.addAttribute("usuarios", usuarios);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "administrador/usuarios";
    }

    //Metodo que nos ayuda en el filtrado de la informacion de las ordenes
    @RequestMapping(value = "/buscarOrden", method = RequestMethod.POST)
    public String buscarOrden(@RequestParam("nro") String nro, Model model) {
        //Dentro de la lista de la clase Orden listaremos la listarOrdenBuscar que estara
        //compuesta de un query
        List<Orden> ordenes = ordensService.listarOrdenBuscar(nro);
        //Agregamos la instancia ordenes al modelo
        model.addAttribute("ordenes", ordenes);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "administrador/ordenes";
    }

    //Metodo que nos ayuda en el filtrado de la informacion de las quejas
    @RequestMapping(value = "/buscarContacto", method = RequestMethod.POST)
    public String buscarContacto(@RequestParam("email") String email, Model model) {
        //Dentro de la lista de la clase Contacto listaremos la listarContactoBuscar que estara
        //compuesta de un query
        List<Contacto> contactos = contactoService.listarContactoBuscar(email);
        //Agregamos la instancia contactos al modelo
        model.addAttribute("contactos", contactos);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "administrador/contactanos";
    }

    @GetMapping("/reportes")
    public String getPieChart(Model model) {
        Map<String, Integer> graphData = new TreeMap<>();
        List<Usuario> usuarios = usuarioService.findAll();
        int contador = 0;
        int conta = 0;
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getTipo().equals("ADMIN")) {
                contador++;
            } else {
                conta++;
            }
            graphData.put("Administrador", contador);
            graphData.put("Usuario", conta);
        }
        model.addAttribute("chartData", graphData);

        Map<String, Integer> graphData1 = new TreeMap<>();
        List<DetalleOrden> detalles = detalleService.findAll();
        int contador1 = 0, contador2 = 0, contador3 = 0, contador4 = 0, contador5 = 0, contador6 = 0,
                contador7 = 0, contador8 = 0, contador9 = 0, contador10 = 0, contador11 = 0, contador12 = 0;
        for (int i = 0; i < detalles.size(); i++) {
            if (detalles.get(i).getNombre().equals("Mozarella")) {
                contador1 = contador1 + (int) detalles.get(i).getCantidad();
            }
            if (detalles.get(i).getNombre().equals("Pepperoni")) {
                contador2 = contador2 + (int) detalles.get(i).getCantidad();
            }
            if (detalles.get(i).getNombre().equals("Pïña")) {
                contador3 = contador3 + (int) detalles.get(i).getCantidad();
            }
            if (detalles.get(i).getNombre().equals("Cuatro Quesos")) {
                contador4 = contador4 + (int) detalles.get(i).getCantidad();
            }
            if (detalles.get(i).getNombre().equals("Margarita")) {
                contador5 = contador5 + (int) detalles.get(i).getCantidad();
            }
            if (detalles.get(i).getNombre().equals("Chanpiñones")) {
                contador6 = contador6 + (int) detalles.get(i).getCantidad();
            }
            if (detalles.get(i).getNombre().equals("Hawaiana")) {
                contador7 = contador7 + (int) detalles.get(i).getCantidad();
            }
            graphData1.put("Mozarella", contador1);
            graphData1.put("Pepperoni", contador2);
            graphData1.put("Pïña", contador3);
            graphData1.put("Cuatro Quesos", contador4);
            graphData1.put("Margarita", contador5);
            graphData1.put("Chanpiñones", contador6);
            graphData1.put("Hawaiana", contador7);
        }
        model.addAttribute("chartDataVentas", graphData1);

        Map<String, Integer> graphData2 = new TreeMap<>();
        List<Orden> ordenes = ordensService.findAll();
        int sum = 0;
        for (int i = 0; i < ordenes.size(); i++) {
            int id = ordenes.get(i).getUsuario().getId();
            String nombre = ordenes.get(i).getUsuario().getNombre();
            if (ordenes.get(i).getUsuario().getId().equals(id)) {
                sum = sum + (int) ordenes.get(i).getTotal();
            }
            graphData2.put(nombre, sum);
        }
        model.addAttribute("chartDataFechas", graphData2);

        return "administrador/reportes";
    }

}
