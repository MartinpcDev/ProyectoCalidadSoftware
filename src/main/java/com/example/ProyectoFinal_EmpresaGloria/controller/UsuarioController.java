package com.example.ProyectoFinal_EmpresaGloria.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ProyectoFinal_EmpresaGloria.model.Orden;
import com.example.ProyectoFinal_EmpresaGloria.model.Usuario;
import com.example.ProyectoFinal_EmpresaGloria.model.Contacto;
import com.example.ProyectoFinal_EmpresaGloria.service.IOrdenService;
import com.example.ProyectoFinal_EmpresaGloria.service.IUsuarioService;
import com.example.ProyectoFinal_EmpresaGloria.service.IContactoService;
import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller //Es un componente de Spring capaz de recibir peticiones http y responderlas.
@RequestMapping("/usuario") //Asignamos el mappeo general conrrespondiente a cada metodo
public class UsuarioController {

    private ArrayList<Contacto> contactos = new ArrayList(); //Declaramos un arrayList de la clase 
    //Contacto el cual sera un tipo de almacenamiento de datos

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class); //Registrador de datos
    //el cual nos ayudara a mostrar los datos en consola en tiempo real

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IUsuarioService usuarioService; //Llamamos a IUsuarioService y lo instanciamos 
    //con usuarioService

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IOrdenService ordenService; //Llamamos a IOrdenService y lo instanciamos 
    //con ordenService

    @Autowired //Nos permite la inyeccion de dependecnias dentro de Spring
    private IContactoService contactoService; //Llamamos a IContactoService y lo instanciamos 
    //con contactoService

    BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder(); //Clase BCryptPasswordEncoder el cual
    //nos permitira encriptar la contraseña del usuario que se registre en el sistema

    //Mapeo de como se hara el llamado de esta seccion de la pagina a traves de la web
    @GetMapping("/registro")
    public String create() { //Creamos el metodo create de tipo Cadena (String)
        return "usuario/registro"; //el cual nos direccionara a la pagina registro
    }

    //Mapeo del metodo que sera utilizado dentro de los archivos html con la notacion th:action 
    @PostMapping("/save") //
    public String save(Usuario usuario, Model model) {
        logger.info("Usuario registro: {}", usuario); //Mostramos los datos del usuario en consola
        usuario.setTipo("USER"); //a la instancia usuario de ka Clase usuario agregamos el tipo de usuario
        //que sera USER
        usuario.setPassword(passEncode.encode(usuario.getPassword())); //Codificamos la contraseña del
        //usuario
        usuarioService.save(usuario); //dentro de la base de datos guardamos los datos del usuario
        return "redirect:/"; //Se redirecciona a la misma seccion de la pagina
    }

    @GetMapping("/login")
    public String login() { //Metodo de tipo string el cual nos llevara a la ventana login del usuario
        return "usuario/login"; //retornamos la ventana de logeo
    }

    @GetMapping("/acceder")
    //Metodo acceder de tipo cadena el cual nos ayudara a verificar el tipo de usuario legeado
    public String acceder(Usuario usuario, HttpSession session, Model model) {
        //Almacenamos dentro del optional el id del usuario logeado
        Optional<Usuario> user = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString()));
        if (user.isPresent()) { //si el usuario a traves del id se encuentra presente 
            session.setAttribute("idusuario", user.get().getId()); //Agregara su id en la session
            if (user.get().getTipo().equals("ADMIN")) { //Verficamos si el usuario es de tipo
                //administrador
                return "redirect:/administrador"; //para poder asignarle la ruta donde se encontraran
                //las paginas que podra abrir
            } else { //Sino el usuario sera de tipo usuario
                return "redirect:/"; //y redireccionamos a la misma pagina
            }
        } else {
            logger.info("Usuario no existe"); //Mostramos el siguiente mensaje en consola
        }

        return "redirect:/"; //al final retornamos la redireccion a la misma pagina
    }
    
    @GetMapping("/compras")
    //Metodo el cual nos permitira obtener las compras de los usuarios 
    public String obtenerCompras(Model model, HttpSession session) {
        //agregamos los datos de la session
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        //Esta linea de codigo nos permitira asociar la compra con el usuario que la realiza
        Usuario usuario = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        //Listamos las ordenes de acuerdo al usuario que la realize
        List<Orden> ordenes = ordenService.findByUsuario(usuario);
        //Agregamos al modelo las ordenes del usuario
        model.addAttribute("ordenes", ordenes);

        return "usuario/compras"; //Retornamos la direccion de la pagina donde queremos mostrar
        //la direccion
    }

    @PostMapping("/guardarContacto")
    //Metodo que nos ayudara a guardar las quejas o recomendaciones que los usuarios realizen
    public String guardarContacto(Contacto contacto, HttpSession session) throws IOException {

        //Capturamos el usuario de la session que realize el registro
        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        //Agregamos a la instancia del contacto el usuario que la realizo
        contacto.setUsuario(u);
        contactoService.save(contacto); //dentro de la base de datos guardamos el registro
        return "redirect:/"; //una vez guardado el registro lo redireccionamos a la misma pagina
    }

    @GetMapping("/detalle/{id}")
    //Metodo que eliminara los detalles de compra que el usuario requiera
    public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model) {
        //Optional almacenara la orden a traves de su id
        Optional<Orden> orden = ordenService.findById(id);

        // Agregamos el detalle de la orden al modelo
        model.addAttribute("detalles", orden.get().getDetalle());

        //session del usuario
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/detallecompra"; //retornamos la pagina donde queremos mostrar la informacion
    }

    @GetMapping("/cerrar")
    //Metodo para cerrar session de la pagina
    public String cerrarSesion(HttpSession session) {
        session.removeAttribute("idusuario"); //removemos el usuario a traves de su id
        return "redirect:/"; //y redireccionamos a la pagina principal
    }
}
