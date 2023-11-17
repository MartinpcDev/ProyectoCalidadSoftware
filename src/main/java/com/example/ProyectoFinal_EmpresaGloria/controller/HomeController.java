package com.example.ProyectoFinal_EmpresaGloria.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ProyectoFinal_EmpresaGloria.model.DetalleOrden;
import com.example.ProyectoFinal_EmpresaGloria.model.Orden;
import com.example.ProyectoFinal_EmpresaGloria.model.Producto;
import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;
import com.example.ProyectoFinal_EmpresaGloria.service.IDetalleOrdenService;
import com.example.ProyectoFinal_EmpresaGloria.service.IOrdenService;
import com.example.ProyectoFinal_EmpresaGloria.service.IUsuarioService;
import com.example.ProyectoFinal_EmpresaGloria.service.IProductoService;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller //Es un componente de Spring capaz de recibir peticiones http y responderlas.
@RequestMapping("/") //Asignamos el mappeo general conrrespondiente a cada metodo
public class HomeController {

    //Logger nos ayudara a mostrar los datos a traves de consola
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IProductoService productoService;

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IUsuarioService usuarioService;

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IOrdenService ordenService;

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IDetalleOrdenService detalleOrdenService;

    // ArrayList que nos ayudara a almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //Instanciamos orden de clase Orden el cual nos ayudara para los datos de la orden
    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model model, HttpSession session) {
        //el log nos mostrara la session del usuario en la consola
        log.info("Sesion del usuario: {}", session.getAttribute("idusuario"));
        log.info("username: {}",session.getAttribute("username"));
        model.addAttribute("username",session.getAttribute("username"));
        //Dentro del modelo listamos todos los datos de productos directamente de la base de datos
        model.addAttribute("productos", productoService.findAll());
        //Dentro del modelo agregamos lo que es la sesion del usuario
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "usuario/home";
    }

    @GetMapping("productohome/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        //el log nos mostrara el id del producto
        log.info("Id producto enviado como parámetro {}", id);
        //Instanciamos el objeto producto de la clase Producto
        Producto producto = new Producto();
        //LLamamos al producto a traves de su id
        Optional<Producto> productoOptional = productoService.get(id);
        //dentro de la instancia producto le asignamos la informacion rescatada de la base de datos
        producto = productoOptional.get();
        //Al modelo le agregamos la informacion obtenida para proceder a mostrarla
        model.addAttribute("producto", producto);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "usuario/productohome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        //Instanciamos detalleOrden de la clase DetalleOrden
        DetalleOrden detalleOrden = new DetalleOrden();
        //Instanciamos producto de la clase Producto
        Producto producto = new Producto();
        //Declaramos la variable sumaTotal de tipo double
        double sumaTotal = 0;

        //Capturamos la informacion del producto a traves de su id
        Optional<Producto> optionalProducto = productoService.get(id);
        //Mostramos la informacion del producto añadido en consola
        log.info("Producto añadido: {}", optionalProducto.get());
        //Mostramos la cantidad del producto en consola
        log.info("Cantidad: {}", cantidad);
        //la instancia producto le asignamos la instancia del optional para poder llamar los datos de la 
        //base de datos
        producto = optionalProducto.get();

        //Dentro de la instancia detalleOrden guardamos la cantidad del producto
        detalleOrden.setCantidad(cantidad);
        //Dentro de la instancia detalleOrden guardamos el precio del producto
        detalleOrden.setPrecio(producto.getPrecio());
        //Dentro de la instancia detalleOrden guardamos el nombre del producto
        detalleOrden.setNombre(producto.getNombre());
        //Dentro de la instancia detalleOrden guardamos el total de compra del producto
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        //Y lo guardamos en la instancia producto
        detalleOrden.setProducto(producto);

        //Validamos que el producto registrado no se añada 2 veces
        Integer idProducto = producto.getId();
        //La variable ingresado de tipo booleano nos ayudara a verificar la existencia del producto
        //anyMatch devolvera si algun dato ya se encuentra registrado
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getId() == idProducto);

        if (!ingresado) { //si el producto no se encuentra
            detalles.add(detalleOrden); //procedera a agregar el detalle de la orden
        }

        //Calculamos la suma total de la compra
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();

        orden.setTotal(sumaTotal); //Agregamos la suma total a la orden
        //Agregamos los detalles de la orden al modelo
        model.addAttribute("cart", detalles);
        //Agregamos la orden al modelo
        model.addAttribute("orden", orden);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "usuario/carrito";
    }

    // Metodo que nos ayuda a eliminar un producto del carrito a traves de su id
    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model) {
        //Lista de los productos que se guardaran en el detalle de la orden
        List<DetalleOrden> ordenesNueva = new ArrayList<DetalleOrden>();
        //Recorremos los detalles de la orden a traves de un for
        for (DetalleOrden detalleOrden : detalles) {
            if (detalleOrden.getProducto().getId() != id) { //si el id de la orden es diferente
                //Procedemos a añadir el detalle de la orden
                ordenesNueva.add(detalleOrden);
            }
        }
        //Nueva lista con los productos restantes
        detalles = ordenesNueva;

        double sumaTotal = 0; //Variable sumaTotal de tipo double que guardara la suma total de la orden 
        sumaTotal = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(sumaTotal); //Agregamos la suma total a la orden
        //Agregamos los detalles de la orden al modelo
        model.addAttribute("cart", detalles);
        //Agregamos la orden al modelo
        model.addAttribute("orden", orden);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {
        //Agregamos los detalles de la orden al modelo
        model.addAttribute("cart", detalles);
        //Agregamos la orden al modelo
        model.addAttribute("orden", orden);
        //Agregamos al modelo la sesion del usuario a traves de su id
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model, HttpSession session) {
        //Capturamos el id del usuario de la session
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        //Agregamos los detalles de la orden al modelo
        model.addAttribute("cart", detalles);
        //Agregamos la orden al modelo
        model.addAttribute("orden", orden);
        //Agregamos los datos del usuario al modelo
        model.addAttribute("usuario", usuario);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "usuario/resumenorden";
    }

    // guardar la orden
    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session) {
        Date fechaCreacion = new Date(); //LLamamos a la clase Date para capturar la fecha del momento
        orden.setFechaCreacion(fechaCreacion); //Agregamos la fecha de creacion a la orden
        //el cual nos ayudara a mostrar en que fecha se registro la orden
        //Agregamos a la orden el numero que tendra la orden
        orden.setNumero(ordenService.generarNumeroOrden());

        //Capturamos el id del usuario de la session
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();

        orden.setUsuario(usuario); //Agregamos a la orden los datos del usuario que genero la orden
        ordenService.save(orden); //guardamos los datos de la orden en la base de datos

        //A traves de este for guardamos cada detalle de orden
        //que se registrara en uns sola orden
        for (DetalleOrden dt : detalles) {
            dt.setOrden(orden); //Guardamos los datos en la orden
            detalleOrdenService.save(dt); //guardamos los detalles de la orden en la base de datos
        }

        ///Limpiamos la lista y la orden
        orden = new Orden();
        detalles.clear();
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "redirect:/";
    }

    @RequestMapping(value = "/buscarProducto", method = RequestMethod.POST)
    //Metodo que nos ayudara para el filtrado de informacion el cual buscara los productos a traves
    //del nombre del producto
    public String buscarProducto(@RequestParam("nombre") String nombre, Model model) {
        //Llamamos a la listarProductoBuscar que tendra el parametro del nombre del producto
        //el metodo contiene un query directo de la base de datos
        List<Producto> productos = productoService.listarProductoBuscar(nombre);
        //Añadimos la informacion al modelo
        model.addAttribute("productos", productos);
        //y retornamos la ruta del archivo html donde queremos que se muestre la informacion
        return "usuario/home";
    }
}
