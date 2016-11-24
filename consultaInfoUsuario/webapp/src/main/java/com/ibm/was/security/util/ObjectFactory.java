//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.8-b130911.1802 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.10.30 a las 10:53:26 AM ART 
//


package com.ibm.was.security.util;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bgba.was package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bgba.was
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ApplicationBinding }
     * 
     */
    public ApplicationBinding createApplicationBinding() {
        return new ApplicationBinding();
    }

    /**
     * Create an instance of {@link ApplicationBinding.AuthorizationTable }
     * 
     */
    public ApplicationBinding.AuthorizationTable createApplicationBindingAuthorizationTable() {
        return new ApplicationBinding.AuthorizationTable();
    }

    /**
     * Create an instance of {@link ApplicationBinding.AuthorizationTable.Authorizations }
     * 
     */
    public ApplicationBinding.AuthorizationTable.Authorizations createApplicationBindingAuthorizationTableAuthorizations() {
        return new ApplicationBinding.AuthorizationTable.Authorizations();
    }

    /**
     * Create an instance of {@link ApplicationBinding.Application }
     * 
     */
    public ApplicationBinding.Application createApplicationBindingApplication() {
        return new ApplicationBinding.Application();
    }

    /**
     * Create an instance of {@link ApplicationBinding.AuthorizationTable.Authorizations.Users }
     * 
     */
    public ApplicationBinding.AuthorizationTable.Authorizations.Users createApplicationBindingAuthorizationTableAuthorizationsUsers() {
        return new ApplicationBinding.AuthorizationTable.Authorizations.Users();
    }

    /**
     * Create an instance of {@link ApplicationBinding.AuthorizationTable.Authorizations.Role }
     * 
     */
    public ApplicationBinding.AuthorizationTable.Authorizations.Role createApplicationBindingAuthorizationTableAuthorizationsRole() {
        return new ApplicationBinding.AuthorizationTable.Authorizations.Role();
    }

    /**
     * Create an instance of {@link ApplicationBinding.AuthorizationTable.Authorizations.Groups }
     * 
     */
    public ApplicationBinding.AuthorizationTable.Authorizations.Groups createApplicationBindingAuthorizationTableAuthorizationsGroups() {
        return new ApplicationBinding.AuthorizationTable.Authorizations.Groups();
    }

}
