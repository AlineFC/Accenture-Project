package cl.accenture.proyecto.services;

import cl.accenture.proyecto.model.Persona;
import cl.accenture.proyecto.model.Rol;
import cl.accenture.proyecto.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    //invoca al JavaMailSender
    private JavaMailSender javaMailSender;

    @Autowired// se crea su constructor
    public NotificationService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /*


    se le define el metodo, y que tipo de objeto recibe, con una excepcion
    y en este caso seria el objeto rol, tambien se crea un email oficial de la empresa para que el administrador tambien
    reciba el email de forma automatica
     */
    public void sendNotificacion(String s, Rol rol) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage(  );
        mail.setTo("innefastcompany@gmail.com");
        mail.setFrom( "innefastcompany@gmail.com" );
        mail.setText( "Mensaje en texto de Prueba uno.. " );
        mail.setSubject( "Notificacion InneFast " );
        javaMailSender.send( mail );
    }

    /*


   se le define el metodo, y que tipo de onjeto recibe, con una excepcion
   y en este caso seria el objeto usuario, tambien se crea un email oficial de la empresa para que el administrador tambien
   reciba el email de forma automatica
    */
    public void sendNotificacion(Usuario usuario) throws MailException {

        /*

        se lo envio al administrador con el correo central de la pagina
        y al la persona obteniendo su email, tambien se puede reemplazar el innefastcompany@gmail.com
        por un usuario.getEmail(); , que en este caso obtendria el correo del administrador
        (tiene mas sentido en personan este era solo una prueba)
         */
        SimpleMailMessage mail = new SimpleMailMessage(  );
        mail.setTo("innefastcompany@gmail.com", usuario.getEmail());
        mail.setFrom( "innefastcampany@gmail.com" );
        mail.setText( "Pasapalabra, este es mi email..  " );
        mail.setSubject( "Notificacion InneFast" );
        javaMailSender.send( mail ); }

    /*


se le define el metodo, y que tipo de onjeto recibe, con una excepcion
y en este caso seria el objeto persona, tambien se crea un email oficial de la empresa para que el administrador tambien
reciba el email de forma automatica

etse metodo se centra en agregar Persona a Proyecto y la base de datos, por eso tiene un Agregar al final del nombre
*/
    public void sendNotificacionA(String s, Persona persona) throws MailException {

         /*

        se lo envio al administrador con el correo central de la pagina
        y a la persona obteniendo su email, tambien se puede reemplazar el innefastcompany@gmail.com
        por un usuario.getEmail(); , que en este caso obtendria el correo del administrador
         */

        SimpleMailMessage mail = new SimpleMailMessage(  );
        mail.setTo("innefastcompany@gmail.com", persona.getEmail());
        mail.setFrom( "innefastcompany@gmail.com");
        mail.setText( "El rut " + persona.getIdPersona() + " a sido agregada a proyecto " + persona.getProyecto() + " de forma exitosa." );
        mail.setSubject( "Notificacion InneFast" );
        javaMailSender.send( mail ); }

    /*


se le define el metodo, y que tipo de objeto recibe, con una excepcion
y en este caso seria el objeto persona, tambien se crea un email oficial de la empresa para que el administrador tambien
reciba el email de forma automatica


este metodo a diferencia del otro de arriba se centra en eliminar, por eso tiene la DELETE al final del metodo.
*/
    public void sendNotificacionD(String s, Persona persona) throws MailException {


        /*

        se lo envio al administrador con el correo central de la pagina
        y a la persona obteniendo su email, tambien se puede reemplazar el innefastcompany@gmail.com
        por un usuario.getEmail(); , que en este caso obtendria el correo del administrador
         */

        SimpleMailMessage mail = new SimpleMailMessage(  );
        mail.setTo("innefastcompany@gmail.com", persona.getEmail());
        mail.setFrom( "innefastcompany@gmail.com");
        mail.setText( "El rut " + persona.getIdPersona() + " a sido eliminado del proyecto " + persona.getProyecto() + " de forma exitosa." );
        mail.setSubject( "Notificacion InneFast" );
        javaMailSender.send( mail ); }
}
