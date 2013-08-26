
package com.example.rest_client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.rest_client package. 
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

    private final static QName _SetUtFacade_QNAME = new QName("http://rest.example.com/", "setUtFacade");
    private final static QName _SetImgFacadeResponse_QNAME = new QName("http://rest.example.com/", "setImgFacadeResponse");
    private final static QName _GetUsuarioId_QNAME = new QName("http://rest.example.com/", "getUsuarioId");
    private final static QName _GetUtFacadeResponse_QNAME = new QName("http://rest.example.com/", "getUtFacadeResponse");
    private final static QName _GetImagenesResponse_QNAME = new QName("http://rest.example.com/", "getImagenesResponse");
    private final static QName _GetImagenes_QNAME = new QName("http://rest.example.com/", "getImagenes");
    private final static QName _IOException_QNAME = new QName("http://rest.example.com/", "IOException");
    private final static QName _GetUsuarioIdResponse_QNAME = new QName("http://rest.example.com/", "getUsuarioIdResponse");
    private final static QName _SetImgFacade_QNAME = new QName("http://rest.example.com/", "setImgFacade");
    private final static QName _SetUtFacadeResponse_QNAME = new QName("http://rest.example.com/", "setUtFacadeResponse");
    private final static QName _GetUtFacade_QNAME = new QName("http://rest.example.com/", "getUtFacade");
    private final static QName _GetImgFacade_QNAME = new QName("http://rest.example.com/", "getImgFacade");
    private final static QName _GetImgFacadeResponse_QNAME = new QName("http://rest.example.com/", "getImgFacadeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.rest_client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetImgFacadeResponse }
     * 
     */
    public GetImgFacadeResponse createGetImgFacadeResponse() {
        return new GetImgFacadeResponse();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link GetImagenes }
     * 
     */
    public GetImagenes createGetImagenes() {
        return new GetImagenes();
    }

    /**
     * Create an instance of {@link GetUsuarioIdResponse }
     * 
     */
    public GetUsuarioIdResponse createGetUsuarioIdResponse() {
        return new GetUsuarioIdResponse();
    }

    /**
     * Create an instance of {@link GetImgFacade }
     * 
     */
    public GetImgFacade createGetImgFacade() {
        return new GetImgFacade();
    }

    /**
     * Create an instance of {@link SetUtFacade }
     * 
     */
    public SetUtFacade createSetUtFacade() {
        return new SetUtFacade();
    }

    /**
     * Create an instance of {@link SetUtFacadeResponse }
     * 
     */
    public SetUtFacadeResponse createSetUtFacadeResponse() {
        return new SetUtFacadeResponse();
    }

    /**
     * Create an instance of {@link SetImgFacade }
     * 
     */
    public SetImgFacade createSetImgFacade() {
        return new SetImgFacade();
    }

    /**
     * Create an instance of {@link GetUtFacade }
     * 
     */
    public GetUtFacade createGetUtFacade() {
        return new GetUtFacade();
    }

    /**
     * Create an instance of {@link GetUtFacadeResponse }
     * 
     */
    public GetUtFacadeResponse createGetUtFacadeResponse() {
        return new GetUtFacadeResponse();
    }

    /**
     * Create an instance of {@link GetImagenesResponse }
     * 
     */
    public GetImagenesResponse createGetImagenesResponse() {
        return new GetImagenesResponse();
    }

    /**
     * Create an instance of {@link SetImgFacadeResponse }
     * 
     */
    public SetImgFacadeResponse createSetImgFacadeResponse() {
        return new SetImgFacadeResponse();
    }

    /**
     * Create an instance of {@link GetUsuarioId }
     * 
     */
    public GetUsuarioId createGetUsuarioId() {
        return new GetUsuarioId();
    }

    /**
     * Create an instance of {@link ImagenData }
     * 
     */
    public ImagenData createImagenData() {
        return new ImagenData();
    }

    /**
     * Create an instance of {@link UsuarioTableFacade }
     * 
     */
    public UsuarioTableFacade createUsuarioTableFacade() {
        return new UsuarioTableFacade();
    }

    /**
     * Create an instance of {@link ImageFacade }
     * 
     */
    public ImageFacade createImageFacade() {
        return new ImageFacade();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUtFacade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "setUtFacade")
    public JAXBElement<SetUtFacade> createSetUtFacade(SetUtFacade value) {
        return new JAXBElement<SetUtFacade>(_SetUtFacade_QNAME, SetUtFacade.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetImgFacadeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "setImgFacadeResponse")
    public JAXBElement<SetImgFacadeResponse> createSetImgFacadeResponse(SetImgFacadeResponse value) {
        return new JAXBElement<SetImgFacadeResponse>(_SetImgFacadeResponse_QNAME, SetImgFacadeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsuarioId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "getUsuarioId")
    public JAXBElement<GetUsuarioId> createGetUsuarioId(GetUsuarioId value) {
        return new JAXBElement<GetUsuarioId>(_GetUsuarioId_QNAME, GetUsuarioId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUtFacadeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "getUtFacadeResponse")
    public JAXBElement<GetUtFacadeResponse> createGetUtFacadeResponse(GetUtFacadeResponse value) {
        return new JAXBElement<GetUtFacadeResponse>(_GetUtFacadeResponse_QNAME, GetUtFacadeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImagenesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "getImagenesResponse")
    public JAXBElement<GetImagenesResponse> createGetImagenesResponse(GetImagenesResponse value) {
        return new JAXBElement<GetImagenesResponse>(_GetImagenesResponse_QNAME, GetImagenesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImagenes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "getImagenes")
    public JAXBElement<GetImagenes> createGetImagenes(GetImagenes value) {
        return new JAXBElement<GetImagenes>(_GetImagenes_QNAME, GetImagenes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUsuarioIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "getUsuarioIdResponse")
    public JAXBElement<GetUsuarioIdResponse> createGetUsuarioIdResponse(GetUsuarioIdResponse value) {
        return new JAXBElement<GetUsuarioIdResponse>(_GetUsuarioIdResponse_QNAME, GetUsuarioIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetImgFacade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "setImgFacade")
    public JAXBElement<SetImgFacade> createSetImgFacade(SetImgFacade value) {
        return new JAXBElement<SetImgFacade>(_SetImgFacade_QNAME, SetImgFacade.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetUtFacadeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "setUtFacadeResponse")
    public JAXBElement<SetUtFacadeResponse> createSetUtFacadeResponse(SetUtFacadeResponse value) {
        return new JAXBElement<SetUtFacadeResponse>(_SetUtFacadeResponse_QNAME, SetUtFacadeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUtFacade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "getUtFacade")
    public JAXBElement<GetUtFacade> createGetUtFacade(GetUtFacade value) {
        return new JAXBElement<GetUtFacade>(_GetUtFacade_QNAME, GetUtFacade.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImgFacade }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "getImgFacade")
    public JAXBElement<GetImgFacade> createGetImgFacade(GetImgFacade value) {
        return new JAXBElement<GetImgFacade>(_GetImgFacade_QNAME, GetImgFacade.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImgFacadeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rest.example.com/", name = "getImgFacadeResponse")
    public JAXBElement<GetImgFacadeResponse> createGetImgFacadeResponse(GetImgFacadeResponse value) {
        return new JAXBElement<GetImgFacadeResponse>(_GetImgFacadeResponse_QNAME, GetImgFacadeResponse.class, null, value);
    }

}
