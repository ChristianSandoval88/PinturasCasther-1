
package com.openbravo.pos.forms;

import com.openbravo.pos.ticket.CategoryInfo;
import com.openbravo.pos.ticket.ProductInfoExt;
import com.openbravo.pos.ticket.TaxInfo;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.openbravo.data.loader.*;
import com.openbravo.format.Formats;
import com.openbravo.basic.BasicException;
import com.openbravo.data.model.Field;
import com.openbravo.data.model.Row;
import com.openbravo.pos.customers.CustomerInfoExt;
import com.openbravo.pos.inventory.AttributeSetInfo;
import com.openbravo.pos.inventory.TaxCustCategoryInfo;
import com.openbravo.pos.inventory.LocationInfo;
import com.openbravo.pos.inventory.MovementReason;
import com.openbravo.pos.inventory.TaxCategoryInfo;
import com.openbravo.pos.mant.FloorsInfo;
import com.openbravo.pos.payment.PaymentInfo;
import com.openbravo.pos.payment.PaymentInfoTicket;
import com.openbravo.pos.ticket.FindTicketsInfo;
import com.openbravo.pos.ticket.TicketTaxInfo;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 *
 * @author adrianromero
 */
public class DataLogicSales extends BeanFactoryDataSingle {

    protected Session s;

    protected Datas[] auxiliarDatas;
    protected Datas[] stockdiaryDatas;
    // protected Datas[] productcatDatas;
    protected Datas[] paymenttabledatas;
    protected Datas[] stockdatas;

    protected Row productsRow;

    /** Creates a new instance of SentenceContainerGeneric */
    public DataLogicSales() {
        stockdiaryDatas = new Datas[] {Datas.STRING, Datas.TIMESTAMP, Datas.INT, Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE};
        paymenttabledatas = new Datas[] {Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.STRING};
        stockdatas = new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.DOUBLE, Datas.DOUBLE};
        auxiliarDatas = new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING, Datas.STRING};

        productsRow = new Row(
                new Field("ID", Datas.STRING, Formats.STRING),
                new Field(AppLocal.getIntString("label.prodref"), Datas.STRING, Formats.STRING, true, true, true),
                new Field(AppLocal.getIntString("label.prodbarcode"), Datas.STRING, Formats.STRING, false, true, true),
                new Field(AppLocal.getIntString("label.prodname"), Datas.STRING, Formats.STRING, true, true, true),
                new Field("ISCOM", Datas.BOOLEAN, Formats.BOOLEAN),
                new Field("ISSCALE", Datas.BOOLEAN, Formats.BOOLEAN),
                new Field(AppLocal.getIntString("label.prodpricebuy"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodcategory"), Datas.STRING, Formats.STRING, false, false, true),
                new Field(AppLocal.getIntString("label.taxcategory"), Datas.STRING, Formats.STRING, false, false, true),
                new Field(AppLocal.getIntString("label.attributeset"), Datas.STRING, Formats.STRING, false, false, true),
                new Field("IMAGE", Datas.IMAGE, Formats.NULL),
                new Field("STOCKCOST", Datas.DOUBLE, Formats.CURRENCY),
                new Field("STOCKVOLUME", Datas.DOUBLE, Formats.DOUBLE),
                new Field("ISCATALOG", Datas.BOOLEAN, Formats.BOOLEAN),
                new Field("CATORDER", Datas.INT, Formats.INT),
                new Field("PROPERTIES", Datas.BYTES, Formats.NULL),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
                new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true),
new Field(AppLocal.getIntString("label.prodpricesell"), Datas.DOUBLE, Formats.CURRENCY, false, true, true)
        );
    }

    public void init(Session s){
        this.s = s;
    }

    public final Row getProductsRow() {
        return productsRow;
    }

    // Utilidades de productos
    public final ProductInfoExt getProductInfo(String id) throws BasicException {
        return (ProductInfoExt) new PreparedSentence(s
            , "SELECT ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, TAXCAT, CATEGORY, ATTRIBUTESET_ID, IMAGE, ATTRIBUTES,PRICESELL1,PRICESELL2,PRICESELL3,PRICESELL4,PRICESELL5,PRICESELL6,PRICESELL7,PRICESELL8,PRICESELL9,PRICESELL10,PRICESELL11,PRICESELL12,PRICESELL13,PRICESELL14,PRICESELL15,PRICESELL16,PRICESELL17,PRICESELL18,PRICESELL19,PRICESELL20,PRICESELL21,PRICESELL22,PRICESELL23,PRICESELL24,PRICESELL25,PRICESELL26,PRICESELL27,PRICESELL28,PRICESELL29,PRICESELL30,PRICESELL31,PRICESELL32,PRICESELL33,PRICESELL34,PRICESELL35,PRICESELL36,PRICESELL37,PRICESELL38,PRICESELL39,PRICESELL40,PRICESELL41,PRICESELL42,PRICESELL43,PRICEBUY1,PRICEBUY2,PRICEBUY3,PRICEBUY4,PRICEBUY5,PRICEBUY6,PRICEBUY7,PRICEBUY8,PRICEBUY9,PRICEBUY10,PRICEBUY11,PRICEBUY12,PRICEBUY13,PRICEBUY14,PRICEBUY15,PRICEBUY16,PRICEBUY17,PRICEBUY18,PRICEBUY19,PRICEBUY20,PRICEBUY21,PRICEBUY22,PRICEBUY1S,PRICEBUY2S,PRICEBUY3S,PRICEBUY4S,PRICEBUY5S,PRICEBUY6S,PRICEBUY7S,PRICEBUY8S,PRICEBUY9S,PRICEBUY10S,PRICEBUY11S,PRICEBUY12S,PRICEBUY13S,PRICEBUY14S,PRICEBUY15S,PRICEBUY16S,PRICEBUY17S,PRICEBUY18S,PRICEBUY19S,PRICEBUY20S,PRICEBUY21S,PRICEBUY22S " +
              "FROM PRODUCTS WHERE ID = ?"
            , SerializerWriteString.INSTANCE
            , ProductInfoExt.getSerializerRead()).find(id);
    }
    public final ProductInfoExt getProductInfoByCode(String sCode) throws BasicException {
        return (ProductInfoExt) new PreparedSentence(s
            , "SELECT ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, TAXCAT, CATEGORY, ATTRIBUTESET_ID, IMAGE, ATTRIBUTES,PRICESELL1,PRICESELL2,PRICESELL3,PRICESELL4,PRICESELL5,PRICESELL6,PRICESELL7,PRICESELL8,PRICESELL9,PRICESELL10,PRICESELL11,PRICESELL12,PRICESELL13,PRICESELL14,PRICESELL15,PRICESELL16,PRICESELL17,PRICESELL18,PRICESELL19,PRICESELL20,PRICESELL21,PRICESELL22,PRICESELL23,PRICESELL24,PRICESELL25,PRICESELL26,PRICESELL27,PRICESELL28,PRICESELL29,PRICESELL30,PRICESELL31,PRICESELL32,PRICESELL33,PRICESELL34,PRICESELL35,PRICESELL36,PRICESELL37,PRICESELL38,PRICESELL39,PRICESELL40,PRICESELL41,PRICESELL42,PRICESELL43,PRICEBUY1,PRICEBUY2,PRICEBUY3,PRICEBUY4,PRICEBUY5,PRICEBUY6,PRICEBUY7,PRICEBUY8,PRICEBUY9,PRICEBUY10,PRICEBUY11,PRICEBUY12,PRICEBUY13,PRICEBUY14,PRICEBUY15,PRICEBUY16,PRICEBUY17,PRICEBUY18,PRICEBUY19,PRICEBUY20,PRICEBUY21,PRICEBUY22,PRICEBUY1S,PRICEBUY2S,PRICEBUY3S,PRICEBUY4S,PRICEBUY5S,PRICEBUY6S,PRICEBUY7S,PRICEBUY8S,PRICEBUY9S,PRICEBUY10S,PRICEBUY11S,PRICEBUY12S,PRICEBUY13S,PRICEBUY14S,PRICEBUY15S,PRICEBUY16S,PRICEBUY17S,PRICEBUY18S,PRICEBUY19S,PRICEBUY20S,PRICEBUY21S,PRICEBUY22S " +
              "FROM PRODUCTS WHERE CODE = ?"
            , SerializerWriteString.INSTANCE
            , ProductInfoExt.getSerializerRead()).find(sCode);
    }
    public final ProductInfoExt getProductInfoByReference(String sReference) throws BasicException {
        return (ProductInfoExt) new PreparedSentence(s
            , "SELECT ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, TAXCAT, CATEGORY, ATTRIBUTESET_ID, IMAGE, ATTRIBUTES,PRICESELL1,PRICESELL2,PRICESELL3,PRICESELL4,PRICESELL5,PRICESELL6,PRICESELL7,PRICESELL8,PRICESELL9,PRICESELL10,PRICESELL11,PRICESELL12,PRICESELL13,PRICESELL14,PRICESELL15,PRICESELL16,PRICESELL17,PRICESELL18,PRICESELL19,PRICESELL20,PRICESELL21,PRICESELL22,PRICESELL23,PRICESELL24,PRICESELL25,PRICESELL26,PRICESELL27,PRICESELL28,PRICESELL29,PRICESELL30,PRICESELL31,PRICESELL32,PRICESELL33,PRICESELL34,PRICESELL35,PRICESELL36,PRICESELL37,PRICESELL38,PRICESELL39,PRICESELL40,PRICESELL41,PRICESELL42,PRICESELL43,PRICEBUY1,PRICEBUY2,PRICEBUY3,PRICEBUY4,PRICEBUY5,PRICEBUY6,PRICEBUY7,PRICEBUY8,PRICEBUY9,PRICEBUY10,PRICEBUY11,PRICEBUY12,PRICEBUY13,PRICEBUY14,PRICEBUY15,PRICEBUY16,PRICEBUY17,PRICEBUY18,PRICEBUY19,PRICEBUY20,PRICEBUY21,PRICEBUY22,PRICEBUY1S,PRICEBUY2S,PRICEBUY3S,PRICEBUY4S,PRICEBUY5S,PRICEBUY6S,PRICEBUY7S,PRICEBUY8S,PRICEBUY9S,PRICEBUY10S,PRICEBUY11S,PRICEBUY12S,PRICEBUY13S,PRICEBUY14S,PRICEBUY15S,PRICEBUY16S,PRICEBUY17S,PRICEBUY18S,PRICEBUY19S,PRICEBUY20S,PRICEBUY21S,PRICEBUY22S " +
              "FROM PRODUCTS WHERE REFERENCE = ?"
            , SerializerWriteString.INSTANCE
            , ProductInfoExt.getSerializerRead()).find(sReference);
    }

    // Catalogo de productos
    public final List<CategoryInfo> getRootCategories21() throws BasicException {
        return new PreparedSentence(s
            , "SELECT ID, NAME, IMAGE FROM CATEGORIES WHERE PARENTID IS NULL ORDER BY NAME"
            , null
            , CategoryInfo.getSerializerRead()).list();
    }
    
    public final List<CategoryInfo> getRootCategories() throws BasicException {
        return new PreparedSentence(s
            , "SELECT ID, NAME, IMAGE FROM CATEGORIES WHERE PARENTID IS NULL ORDER BY NAME"
            , null
            , CategoryInfo.getSerializerRead()).list();
    }
    public final List<CategoryInfo> getSubcategories(String category) throws BasicException  {
        return new PreparedSentence(s
            , "SELECT ID, NAME, IMAGE FROM CATEGORIES WHERE PARENTID = ? ORDER BY NAME"
            , SerializerWriteString.INSTANCE
            , CategoryInfo.getSerializerRead()).list(category);
    }
    public List<ProductInfoExt> getProductCatalog(String category) throws BasicException  {
        return new PreparedSentence(s
            , "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.TAXCAT, P.CATEGORY, P.ATTRIBUTESET_ID, P.IMAGE, P.ATTRIBUTES,P.PRICESELL1,P.PRICESELL2,P.PRICESELL3,P.PRICESELL4,PRICESELL5,PRICESELL6,PRICESELL7,PRICESELL8,PRICESELL9,PRICESELL10,PRICESELL11,PRICESELL12,PRICESELL13,PRICESELL14,PRICESELL15,PRICESELL16,PRICESELL17,PRICESELL18,PRICESELL19,PRICESELL20,PRICESELL21,PRICESELL22,PRICESELL23,PRICESELL24,PRICESELL25,PRICESELL26,PRICESELL27,PRICESELL28,PRICESELL29,PRICESELL30,PRICESELL31,PRICESELL32,PRICESELL33,PRICESELL34,PRICESELL35,PRICESELL36,PRICESELL37,PRICESELL38,PRICESELL39,PRICESELL40,PRICESELL41,PRICESELL42,PRICESELL43,PRICEBUY1,PRICEBUY2,PRICEBUY3,PRICEBUY4,PRICEBUY5,PRICEBUY6,PRICEBUY7,PRICEBUY8,PRICEBUY9,PRICEBUY10,PRICEBUY11,PRICEBUY12,PRICEBUY13,PRICEBUY14,PRICEBUY15,PRICEBUY16,PRICEBUY17,PRICEBUY18,PRICEBUY19,PRICEBUY20,PRICEBUY21,PRICEBUY22,PRICEBUY1S,PRICEBUY2S,PRICEBUY3S,PRICEBUY4S,PRICEBUY5S,PRICEBUY6S,PRICEBUY7S,PRICEBUY8S,PRICEBUY9S,PRICEBUY10S,PRICEBUY11S,PRICEBUY12S,PRICEBUY13S,PRICEBUY14S,PRICEBUY15S,PRICEBUY16S,PRICEBUY17S,PRICEBUY18S,PRICEBUY19S,PRICEBUY20S,PRICEBUY21S,PRICEBUY22S " +
              "FROM PRODUCTS P, PRODUCTS_CAT O WHERE P.ID = O.PRODUCT AND P.CATEGORY = ? " +
              "ORDER BY O.CATORDER, P.NAME"
            , SerializerWriteString.INSTANCE
            , ProductInfoExt.getSerializerRead()).list(category);
    }
    public List<ProductInfoExt> getProductComments(String id) throws BasicException {
        return new PreparedSentence(s
            , "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.TAXCAT, P.CATEGORY, P.ATTRIBUTESET_ID, P.IMAGE, P.ATTRIBUTES,P.PRICESELL1,P.PRICESELL2,P.PRICESELL3,P.PRICESELL4,P.PRICESELL5,P.PRICESELL6,P.PRICESELL7,P.PRICESELL8,P.PRICESELL9,P.PRICESELL10,P.PRICESELL11,P.PRICESELL12,P.PRICESELL13,P.PRICESELL14,P.PRICESELL15,P.PRICESELL16,P.PRICESELL17,P.PRICESELL18,P.PRICESELL19,P.PRICESELL20,P.PRICESELL21,P.PRICESELL22,P.PRICESELL23,P.PRICESELL24,P.PRICESELL25,P.PRICESELL26,P.PRICESELL27,P.PRICESELL28,P.PRICESELL29,P.PRICESELL30,P.PRICESELL31,P.PRICESELL32,P.PRICESELL33,P.PRICESELL34,P.PRICESELL35,P.PRICESELL36,P.PRICESELL37,P.PRICESELL38,P.PRICESELL39,P.PRICESELL40,P.PRICESELL41,P.PRICESELL42,P.PRICESELL43,P.PRICEBUY1,P.PRICEBUY2,P.PRICEBUY3,P.PRICEBUY4,P.PRICEBUY5,P.PRICEBUY6,P.PRICEBUY7,P.PRICEBUY8,P.PRICEBUY9,P.PRICEBUY10,P.PRICEBUY11,P.PRICEBUY12,P.PRICEBUY13,P.PRICEBUY14,P.PRICEBUY15,P.PRICEBUY16,P.PRICEBUY17,P.PRICEBUY18,P.PRICEBUY19,P.PRICEBUY20,P.PRICEBUY21,P.PRICEBUY22,P.PRICEBUY1S,P.PRICEBUY2S,P.PRICEBUY3S,P.PRICEBUY4S,P.PRICEBUY5S,P.PRICEBUY6S,P.PRICEBUY7S,P.PRICEBUY8S,P.PRICEBUY9S,P.PRICEBUY10S,P.PRICEBUY11S,P.PRICEBUY12S,P.PRICEBUY13S,P.PRICEBUY14S,P.PRICEBUY15S,P.PRICEBUY16S,P.PRICEBUY17S,P.PRICEBUY18S,P.PRICEBUY19S,P.PRICEBUY20S,P.PRICEBUY21S,P.PRICEBUY22S " +
              "FROM PRODUCTS P, PRODUCTS_CAT O, PRODUCTS_COM M WHERE P.ID = O.PRODUCT AND P.ID = M.PRODUCT2 AND M.PRODUCT = ? " +
              "AND P.ISCOM = " + s.DB.TRUE() + " " +
              "ORDER BY O.CATORDER, P.NAME"
            , SerializerWriteString.INSTANCE
            , ProductInfoExt.getSerializerRead()).list(id);
    }
  
    // Products list
    public final SentenceList getProductList() {
        return new StaticSentence(s
            , new QBFBuilder(
              "SELECT ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, TAXCAT, CATEGORY, ATTRIBUTESET_ID, IMAGE, ATTRIBUTES,PRICESELL1,PRICESELL2,PRICESELL3,PRICESELL4,PRICESELL5,PRICESELL6,PRICESELL7,PRICESELL8,PRICESELL9,PRICESELL10,PRICESELL11,PRICESELL12,PRICESELL13,PRICESELL14,PRICESELL15,PRICESELL16,PRICESELL17,PRICESELL18,PRICESELL19,PRICESELL20,PRICESELL21,PRICESELL22,PRICESELL23,PRICESELL24,PRICESELL25,PRICESELL26,PRICESELL27,PRICESELL28,PRICESELL29,PRICESELL30,PRICESELL31,PRICESELL32,PRICESELL33,PRICESELL34,PRICESELL35,PRICESELL36,PRICESELL37,PRICESELL38,PRICESELL39,PRICESELL40,PRICESELL41,PRICESELL42,PRICESELL43,PRICEBUY1,PRICEBUY2,PRICEBUY3,PRICEBUY4,PRICEBUY5,PRICEBUY6,PRICEBUY7,PRICEBUY8,PRICEBUY9,PRICEBUY10,PRICEBUY11,PRICEBUY12,PRICEBUY13,PRICEBUY14,PRICEBUY15,PRICEBUY16,PRICEBUY17,PRICEBUY18,PRICEBUY19,PRICEBUY20,PRICEBUY21,PRICEBUY22,PRICEBUY1S,PRICEBUY2S,PRICEBUY3S,PRICEBUY4S,PRICEBUY5S,PRICEBUY6S,PRICEBUY7S,PRICEBUY8S,PRICEBUY9S,PRICEBUY10S,PRICEBUY11S,PRICEBUY12S,PRICEBUY13S,PRICEBUY14S,PRICEBUY15S,PRICEBUY16S,PRICEBUY17S,PRICEBUY18S,PRICEBUY19S,PRICEBUY20S,PRICEBUY21S,PRICEBUY22S " +
              "FROM PRODUCTS WHERE ?(QBF_FILTER) ORDER BY REFERENCE", new String[] {"NAME", "PRICEBUY", "PRICESELL", "CATEGORY", "CODE"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , ProductInfoExt.getSerializerRead());
    }
    public List<ProductInfoExt> getProductFilter(String filter) throws BasicException  {
        return new PreparedSentence(s
            , "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.TAXCAT, P.CATEGORY, P.ATTRIBUTESET_ID, P.IMAGE, P.ATTRIBUTES,P.PRICESELL1,P.PRICESELL2,P.PRICESELL3,P.PRICESELL4,P.PRICESELL5,P.PRICESELL6,P.PRICESELL7,P.PRICESELL8,P.PRICESELL9,P.PRICESELL10,P.PRICESELL11,P.PRICESELL12,P.PRICESELL13,P.PRICESELL14,P.PRICESELL15,P.PRICESELL16,P.PRICESELL17,P.PRICESELL18,P.PRICESELL19,P.PRICESELL20,P.PRICESELL21,P.PRICESELL22,P.PRICESELL23,P.PRICESELL24,P.PRICESELL25,P.PRICESELL26,P.PRICESELL27,P.PRICESELL28,P.PRICESELL29,P.PRICESELL30,P.PRICESELL31,P.PRICESELL32,P.PRICESELL33,P.PRICESELL34,P.PRICESELL35,P.PRICESELL36,P.PRICESELL37,P.PRICESELL38,P.PRICESELL39,P.PRICESELL40,P.PRICESELL41,P.PRICESELL42,P.PRICESELL43,P.PRICEBUY1,P.PRICEBUY2,P.PRICEBUY3,P.PRICEBUY4,P.PRICEBUY5,P.PRICEBUY6,P.PRICEBUY7,P.PRICEBUY8,P.PRICEBUY9,P.PRICEBUY10,P.PRICEBUY11,P.PRICEBUY12,P.PRICEBUY13,P.PRICEBUY14,P.PRICEBUY15,P.PRICEBUY16,P.PRICEBUY17,P.PRICEBUY18,P.PRICEBUY19,P.PRICEBUY20,P.PRICEBUY21,P.PRICEBUY22,P.PRICEBUY1S,P.PRICEBUY2S,P.PRICEBUY3S,P.PRICEBUY4S,P.PRICEBUY5S,P.PRICEBUY6S,P.PRICEBUY7S,P.PRICEBUY8S,P.PRICEBUY9S,P.PRICEBUY10S,P.PRICEBUY11S,P.PRICEBUY12S,P.PRICEBUY13S,P.PRICEBUY14S,P.PRICEBUY15S,P.PRICEBUY16S,P.PRICEBUY17S,P.PRICEBUY18S,P.PRICEBUY19S,P.PRICEBUY20S,P.PRICEBUY21S,P.PRICEBUY22S " +
              "FROM PRODUCTS P, PRODUCTS_CAT O WHERE 1=? AND P.ID = O.PRODUCT AND P.NAME LIKE '%"+filter+"%' " +
              "ORDER BY P.NAME"
                    //"ORDER BY P.PRICESELL"
            , SerializerWriteString.INSTANCE
            , ProductInfoExt.getSerializerRead()).list("1");
    }
    // Products list
    public SentenceList getProductListNormal() {
        return new StaticSentence(s
            , new QBFBuilder(
              "SELECT ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, TAXCAT, CATEGORY, ATTRIBUTESET_ID, IMAGE, ATTRIBUTES,PRICESELL1,PRICESELL2,PRICESELL3,PRICESELL4,PRICESELL5,PRICESELL6,PRICESELL7,PRICESELL8,PRICESELL9,PRICESELL10,PRICESELL11,PRICESELL12,PRICESELL13,PRICESELL14,PRICESELL15,PRICESELL16,PRICESELL17,PRICESELL18,PRICESELL19,PRICESELL20,PRICESELL21,PRICESELL22,PRICESELL23,PRICESELL24,PRICESELL25,PRICESELL26,PRICESELL27,PRICESELL28,PRICESELL29,PRICESELL30,PRICESELL31,PRICESELL32,PRICESELL33,PRICESELL34,PRICESELL35,PRICESELL36,PRICESELL37,PRICESELL38,PRICESELL39,PRICESELL40,PRICESELL41,PRICESELL42,PRICESELL43,P.PRICEBUY1,P.PRICEBUY2,P.PRICEBUY3,P.PRICEBUY4,PRICEBUY5,PRICEBUY6,PRICEBUY7,PRICEBUY8,PRICEBUY9,PRICEBUY10,PRICEBUY11,PRICEBUY12,PRICEBUY13,PRICEBUY14,PRICEBUY15,PRICEBUY16,PRICEBUY17,PRICEBUY18,PRICEBUY19,PRICEBUY20,PRICEBUY21,PRICEBUY22,PRICEBUY1S,PRICEBUY2S,PRICEBUY3S,PRICEBUY4S,PRICEBUY5S,PRICEBUY6S,PRICEBUY7S,PRICEBUY8S,PRICEBUY9S,PRICEBUY10S,PRICEBUY11S,PRICEBUY12S,PRICEBUY13S,PRICEBUY14S,PRICEBUY15S,PRICEBUY16S,PRICEBUY17S,PRICEBUY18S,PRICEBUY19S,PRICEBUY20S,PRICEBUY21S,PRICEBUY22S " +
              "FROM PRODUCTS WHERE ISCOM = " + s.DB.FALSE() + " AND ?(QBF_FILTER) ORDER BY REFERENCE", new String[] {"NAME", "PRICEBUY", "PRICESELL", "CATEGORY", "CODE"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , ProductInfoExt.getSerializerRead());
    }
    
    //Auxiliar list for a filter
    public SentenceList getProductListAuxiliar() {
         return new StaticSentence(s
            , new QBFBuilder(
              "SELECT ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, TAXCAT, CATEGORY, ATTRIBUTESET_ID, IMAGE, ATTRIBUTES,PRICESELL1,PRICESELL2,PRICESELL3,PRICESELL4,PRICESELL5,PRICESELL6,PRICESELL7,PRICESELL8,PRICESELL9,PRICESELL10,PRICESELL11,PRICESELL12,PRICESELL13,PRICESELL14,PRICESELL15,PRICESELL16,PRICESELL17,PRICESELL18,PRICESELL19,PRICESELL20,PRICESELL21,PRICESELL22,PRICESELL23,PRICESELL24,PRICESELL25,PRICESELL26,PRICESELL27,PRICESELL28,PRICESELL29,PRICESELL30,PRICESELL31,PRICESELL32,PRICESELL33,PRICESELL34,PRICESELL35,PRICESELL36,PRICESELL37,PRICESELL38,PRICESELL39,PRICESELL40,PRICESELL41,PRICESELL42,PRICESELL43,P.PRICEBUY1,P.PRICEBUY2,P.PRICEBUY3,P.PRICEBUY4,PRICEBUY5,PRICEBUY6,PRICEBUY7,PRICEBUY8,PRICEBUY9,PRICEBUY10,PRICEBUY11,PRICEBUY12,PRICEBUY13,PRICEBUY14,PRICEBUY15,PRICEBUY16,PRICEBUY17,PRICEBUY18,PRICEBUY19,PRICEBUY20,PRICEBUY21,PRICEBUY22,PRICEBUY1S,PRICEBUY2S,PRICEBUY3S,PRICEBUY4S,PRICEBUY5S,PRICEBUY6S,PRICEBUY7S,PRICEBUY8S,PRICEBUY9S,PRICEBUY10S,PRICEBUY11S,PRICEBUY12S,PRICEBUY13S,PRICEBUY14S,PRICEBUY15S,PRICEBUY16S,PRICEBUY17S,PRICEBUY18S,PRICEBUY19S,PRICEBUY20S,PRICEBUY21S,PRICEBUY22S " +
              "FROM PRODUCTS WHERE ISCOM = " + s.DB.TRUE() + " AND ?(QBF_FILTER) ORDER BY REFERENCE", new String[] {"NAME", "PRICEBUY", "PRICESELL", "CATEGORY", "CODE"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , ProductInfoExt.getSerializerRead());
    }
    
    //Tickets and Receipt list
    
    public SentenceList getTicketsList() {
         return new StaticSentence(s
            , new QBFBuilder(
            "SELECT T.TICKETID, T.TICKETTYPE, R.DATENEW, P.NAME, C.NAME, SUM(PM.TOTAL) "+ 
            "FROM RECEIPTS R JOIN TICKETS T ON R.ID = T.ID LEFT OUTER JOIN PAYMENTS PM ON R.ID = PM.RECEIPT LEFT OUTER JOIN CUSTOMERS C ON C.ID = T.CUSTOMER LEFT OUTER JOIN PEOPLE P ON T.PERSON = P.ID " +
            "WHERE ?(QBF_FILTER) GROUP BY T.ID, T.TICKETID, T.TICKETTYPE, R.DATENEW, P.NAME, C.NAME ORDER BY R.DATENEW DESC, T.TICKETID", new String[] {"T.TICKETID", "T.TICKETTYPE", "PM.TOTAL", "R.DATENEW", "R.DATENEW", "P.NAME", "C.NAME"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.INT, Datas.OBJECT, Datas.INT, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.TIMESTAMP, Datas.OBJECT, Datas.TIMESTAMP, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , new SerializerReadClass(FindTicketsInfo.class));
    }
    
    //User list
    public final SentenceList getClosedCash() {
        return new StaticSentence(s
            , "SELECT HOSTSEQUENCE FROM CLOSEDCASH WHERE e IS NULL ORDER BY HOSTSEQUENCE"
            , null
            , new SerializerRead() { public Object readValues(DataRead dr) throws BasicException {
                return new TaxCategoryInfo(
                        dr.getString(1), 
                        "");
            }});
    }
    
    //User list
    public final SentenceList getUserList() {
        return new StaticSentence(s
            , "SELECT ID, NAME FROM PEOPLE ORDER BY NAME"
            , null
            , new SerializerRead() { public Object readValues(DataRead dr) throws BasicException {
                return new TaxCategoryInfo(
                        dr.getString(1), 
                        dr.getString(2));
            }});
    }
   
    // Listados para combo
    public final SentenceList getTaxList() {
        return new StaticSentence(s
            , "SELECT ID, NAME, CATEGORY, VALIDFROM, CUSTCATEGORY, PARENTID, RATE, RATECASCADE, RATEORDER FROM TAXES ORDER BY NAME"
            , null
            , new SerializerRead() { public Object readValues(DataRead dr) throws BasicException {
                return new TaxInfo(
                        dr.getString(1), 
                        dr.getString(2),
                        dr.getString(3),
                        dr.getTimestamp(4),
                        dr.getString(5),
                        dr.getString(6),
                        dr.getDouble(7).doubleValue(),
                        dr.getBoolean(8).booleanValue(),
                        dr.getInt(9));
            }});
    }
    public final SentenceList getCategoriesList() {
        return new StaticSentence(s
            , "SELECT ID, NAME, IMAGE FROM CATEGORIES ORDER BY NAME"
            , null
            , CategoryInfo.getSerializerRead());
    }
    public final SentenceList getTaxCustCategoriesList() {
        return new StaticSentence(s
            , "SELECT ID, NAME FROM TAXCUSTCATEGORIES ORDER BY NAME"
            , null
            , new SerializerRead() { public Object readValues(DataRead dr) throws BasicException {
                return new TaxCustCategoryInfo(dr.getString(1), dr.getString(2));
            }});
    }
    public final SentenceList getTaxCategoriesList() {
        return new StaticSentence(s
            , "SELECT ID, NAME FROM TAXCATEGORIES ORDER BY NAME"
            , null
            , new SerializerRead() { public Object readValues(DataRead dr) throws BasicException {
                return new TaxCategoryInfo(dr.getString(1), dr.getString(2));
            }});
    }
    public final SentenceList getAttributeSetList() {
        return new StaticSentence(s
            , "SELECT ID, NAME FROM ATTRIBUTESET ORDER BY NAME"
            , null
            , new SerializerRead() { public Object readValues(DataRead dr) throws BasicException {
                return new AttributeSetInfo(dr.getString(1), dr.getString(2));
            }});
    }
    public final SentenceList getLocationsList() {
        return new StaticSentence(s
            , "SELECT ID, NAME, ADDRESS FROM LOCATIONS ORDER BY NAME"
            , null
            , new SerializerReadClass(LocationInfo.class));
    }
    public final SentenceList getFloorsList() {
        return new StaticSentence(s
            , "SELECT ID, NAME FROM FLOORS ORDER BY NAME"
            , null
            , new SerializerReadClass(FloorsInfo.class));
    }

    public CustomerInfoExt findCustomerExt(String card) throws BasicException {
        return (CustomerInfoExt) new PreparedSentence(s
                , "SELECT ID, TAXID, SEARCHKEY, NAME, CARD, TAXCATEGORY, NOTES, MAXDEBT, VISIBLE, CURDATE, CURDEBT" +
                  ", FIRSTNAME, LASTNAME, EMAIL, PHONE, PHONE2, FAX" +
                  ", ADDRESS, ADDRESS2, POSTAL, CITY, REGION, COUNTRY" +
                  " FROM CUSTOMERS WHERE CARD = ? AND VISIBLE = " + s.DB.TRUE()
                , SerializerWriteString.INSTANCE
                , new CustomerExtRead()).find(card);
    }

    public CustomerInfoExt loadCustomerExt(String id) throws BasicException {
        return (CustomerInfoExt) new PreparedSentence(s
                , "SELECT ID, TAXID, SEARCHKEY, NAME, CARD, TAXCATEGORY, NOTES, MAXDEBT, VISIBLE, CURDATE, CURDEBT" +
                  ", FIRSTNAME, LASTNAME, EMAIL, PHONE, PHONE2, FAX" +
                  ", ADDRESS, ADDRESS2, POSTAL, CITY, REGION, COUNTRY" +
                " FROM CUSTOMERS WHERE ID = ?"
                , SerializerWriteString.INSTANCE
                , new CustomerExtRead()).find(id);
    }

    public final boolean isCashActive(String id) throws BasicException {

        return new PreparedSentence(s,
                "SELECT MONEY FROM CLOSEDCASH WHERE DATEEND IS NULL AND MONEY = ?",
                SerializerWriteString.INSTANCE,
                SerializerReadString.INSTANCE).find(id)
            != null;
    }

    public final TicketInfo loadTicket(final int tickettype, final int ticketid) throws BasicException {
        TicketInfo ticket = (TicketInfo) new PreparedSentence(s
                , "SELECT T.ID, T.TICKETTYPE, T.TICKETID, R.DATENEW, R.MONEY, R.ATTRIBUTES, P.ID, P.NAME, T.CUSTOMER FROM RECEIPTS R JOIN TICKETS T ON R.ID = T.ID LEFT OUTER JOIN PEOPLE P ON T.PERSON = P.ID WHERE T.TICKETTYPE = ? AND T.TICKETID = ?"
                , SerializerWriteParams.INSTANCE
                , new SerializerReadClass(TicketInfo.class))
                .find(new DataParams() { public void writeValues() throws BasicException {
                    setInt(1, tickettype);
                    setInt(2, ticketid);
                }});
        if (ticket != null) {

            String customerid = ticket.getCustomerId();
            ticket.setCustomer(customerid == null
                    ? null
                    : loadCustomerExt(customerid));

            ticket.setLines(new PreparedSentence(s
                , "SELECT L.TICKET, L.LINE, L.PRODUCT, L.ATTRIBUTESETINSTANCE_ID, L.UNITS, L.PRICE, T.ID, T.NAME, T.CATEGORY, T.VALIDFROM, T.CUSTCATEGORY, T.PARENTID, T.RATE, T.RATECASCADE, T.RATEORDER, L.ATTRIBUTES, L.TIPO, L.ENVASE, L.PRICEBUY " +
                  "FROM TICKETLINES L, TAXES T WHERE L.TAXID = T.ID AND L.TICKET = ? ORDER BY L.LINE"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(TicketLineInfo.class)).list(ticket.getId()));
            ticket.setPayments(new PreparedSentence(s
                , "SELECT PAYMENT, TOTAL, TRANSID FROM PAYMENTS WHERE RECEIPT = ?"
                , SerializerWriteString.INSTANCE
                , new SerializerReadClass(PaymentInfoTicket.class)).list(ticket.getId()));
        }
        return ticket;
    }
    
    public final void saveTicket2(final TicketInfo ticket, final String location) throws BasicException {
        System.out.println("Entra a ticket2");
        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {

                // Set Receipt Id
                
                if (ticket.getTicketId() == 0) {
                    switch (ticket.getTicketType()) {
                        case TicketInfo.RECEIPT_NORMAL:
                            ticket.setTicketId(getNextTicketIndex().intValue());
                            break;
                        case TicketInfo.RECEIPT_REFUND:
                            ticket.setTicketId(getNextTicketRefundIndex().intValue());
                            break;
                        case TicketInfo.RECEIPT_PAYMENT:
                            ticket.setTicketId(getNextTicketPaymentIndex().intValue());
                            break;
                        default:
                            throw new BasicException();
                    }
                }

                // new receipt
                new PreparedSentence(s
                    , "INSERT INTO RECEIPTS (ID, MONEY, DATENEW, ATTRIBUTES) VALUES (?, ?, ?, ?)"
                    , SerializerWriteParams.INSTANCE
                    ).exec(new DataParams() { public void writeValues() throws BasicException {
                        setString(1, ticket.getId());
                        setString(2, ticket.getActiveCash());
                        setTimestamp(3, ticket.getDate());
                        try {
                            ByteArrayOutputStream o = new ByteArrayOutputStream();
                            ticket.getProperties().storeToXML(o, AppLocal.APP_NAME, "UTF-8");
                            setBytes(4, o.toByteArray());
                        } catch (IOException e) {
                            setBytes(4, null);
                        }
                    }});
                
                // new ticket
                new PreparedSentence(s
                    , "INSERT INTO TICKETS (ID, TICKETTYPE, TICKETID, PERSON, CUSTOMER) VALUES (?, ?, ?, ?, ?)"
                    , SerializerWriteParams.INSTANCE
                    ).exec(new DataParams() { public void writeValues() throws BasicException {
                        setString(1, ticket.getId());
                        setInt(2, ticket.getTicketType());
                        setInt(3, ticket.getTicketId());
                        setString(4, ticket.getUser().getId());
                        setString(5, ticket.getCustomerId());
                    }});

                SentenceExec ticketlineinsert = new PreparedSentence(s
                    , "INSERT INTO TICKETLINES (TICKET, LINE, PRODUCT, ATTRIBUTESETINSTANCE_ID, UNITS, PRICE, TAXID, ATTRIBUTES, TIPO, ENVASE, PRICEBUY) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)"
                    , SerializerWriteBuilder.INSTANCE);

                for (TicketLineInfo l : ticket.getLines()) {
                    ticketlineinsert.exec(l);
                    if (l.getProductID() != null)  {
                        
                    }
                }

                SentenceExec paymentinsert = new PreparedSentence(s
                    , "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, TRANSID, RETURNMSG) VALUES (?, ?, ?, ?, ?, ?)"
                    , SerializerWriteParams.INSTANCE);
                for (final PaymentInfo p : ticket.getPayments()) {
                    paymentinsert.exec(new DataParams() { public void writeValues() throws BasicException {
                        setString(1, UUID.randomUUID().toString());
                        setString(2, ticket.getId());
                        setString(3, p.getName());
                        setDouble(4, p.getTotal());
                        setString(5, ticket.getTransactionID());
                        setBytes(6, (byte[]) Formats.BYTEA.parseValue(ticket.getReturnMessage()));
                    }});

                    if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {

                        // udate customer fields...
                        ticket.getCustomer().updateCurDebt(p.getTotal(), ticket.getDate());

                        // save customer fields...
                        getDebtUpdate().exec(new DataParams() { public void writeValues() throws BasicException {
                            setDouble(1, ticket.getCustomer().getCurdebt());
                            setTimestamp(2, ticket.getCustomer().getCurdate());
                            setString(3, ticket.getCustomer().getId());
                        }});
                    }
                }

                SentenceExec taxlinesinsert = new PreparedSentence(s
                        , "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT)  VALUES (?, ?, ?, ?, ?)"
                        , SerializerWriteParams.INSTANCE);
                if (ticket.getTaxes() != null) {
                    for (final TicketTaxInfo tickettax: ticket.getTaxes()) {
                        taxlinesinsert.exec(new DataParams() { public void writeValues() throws BasicException {
                            setString(1, UUID.randomUUID().toString());
                            setString(2, ticket.getId());
                            setString(3, tickettax.getTaxInfo().getId());
                            setDouble(4, tickettax.getSubTotal());
                            setDouble(5, tickettax.getTax());
                        }});
                    }
                }

                return null;
            }
        };
        t.execute();
    }

    public final void saveTicket(final TicketInfo ticket, final String location) throws BasicException {

        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {

                // Set Receipt Id
                if (ticket.getTicketId() == 0) {
                    switch (ticket.getTicketType()) {
                        case TicketInfo.RECEIPT_NORMAL:
                            ticket.setTicketId(getNextTicketIndex().intValue());
                            break;
                        case TicketInfo.RECEIPT_REFUND:
                            ticket.setTicketId(getNextTicketRefundIndex().intValue());
                            break;
                        case TicketInfo.RECEIPT_PAYMENT:
                            ticket.setTicketId(getNextTicketPaymentIndex().intValue());
                            break;
                        default:
                            throw new BasicException();
                    }
                }

                // new receipt
                new PreparedSentence(s
                    , "INSERT INTO RECEIPTS (ID, MONEY, DATENEW, ATTRIBUTES) VALUES (?, ?, ?, ?)"
                    , SerializerWriteParams.INSTANCE
                    ).exec(new DataParams() { public void writeValues() throws BasicException {
                        setString(1, ticket.getId());
                        setString(2, ticket.getActiveCash());
                        setTimestamp(3, ticket.getDate());
                        try {
                            ByteArrayOutputStream o = new ByteArrayOutputStream();
                            ticket.getProperties().storeToXML(o, AppLocal.APP_NAME, "UTF-8");
                            setBytes(4, o.toByteArray());
                        } catch (IOException e) {
                            setBytes(4, null);
                        }
                    }});
                
                // new ticket
                new PreparedSentence(s
                    , "INSERT INTO TICKETS (ID, TICKETTYPE, TICKETID, PERSON, CUSTOMER) VALUES (?, ?, ?, ?, ?)"
                    , SerializerWriteParams.INSTANCE
                    ).exec(new DataParams() { public void writeValues() throws BasicException {
                        setString(1, ticket.getId());
                        setInt(2, ticket.getTicketType());
                        setInt(3, ticket.getTicketId());
                        setString(4, ticket.getUser().getId());
                        setString(5, ticket.getCustomerId());
                    }});

                SentenceExec ticketlineinsert = new PreparedSentence(s
                    , "INSERT INTO TICKETLINES (ID,TICKET, LINE, PRODUCT, ATTRIBUTESETINSTANCE_ID, UNITS, PRICE, TAXID, ATTRIBUTES, TIPO, ENVASE, PRICEBUY) VALUES (UUID(),?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)"
                    , SerializerWriteBuilder.INSTANCE);

                for (TicketLineInfo l : ticket.getLines()) {
                    ticketlineinsert.exec(l);
                    if (l.getProductID() != null)  {
                        // update the stock
                        if(!l.getTipo().trim().equals(""))
                        {
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            l.getProductID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()* Double.parseDouble(l.getTipo().toString()) ),
                            new Double(l.getPrice())
                            });
                        }
                        
                        else
                        {
                            getStockDiaryInsert().exec(new Object[] {
                                UUID.randomUUID().toString(),
                                ticket.getDate(),
                                l.getMultiply() < 0.0
                                    ? MovementReason.IN_REFUND.getKey()
                                    : MovementReason.OUT_SALE.getKey(),
                                location,
                                l.getProductID(),
                                l.getProductAttSetInstId(),
                                new Double(-l.getMultiply()),
                                new Double(l.getPrice())
                            });
                        }
                        /*
                        if(l.getEnvase().equals("CON ENVASE")&&(l.getTipo().trim().equals("0.250")
                                ||l.getTipo().trim().equals("0.500")
                                ||l.getTipo().trim().equals("1")
                                ||l.getTipo().trim().equals("4")
                                ||l.getTipo().trim().equals("200")))
                        {
                            ProductInfoExt p = getProductInfoByCode(l.getTipo().toString());
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()),
                            new Double("0.00")
                            });
                        }
                        
                        if(l.getEnvase().equals("CON ENVASE")&&(l.getTipo().trim().equals("2")
                                ))
                        {
                            ProductInfoExt p = getProductInfoByCode("1");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()*2),
                            new Double("0.00")
                            });
                        }
                        
                        if(l.getEnvase().equals("CON ENVASE")&&(l.getTipo().trim().equals("3")))
                        {
                            ProductInfoExt p = getProductInfoByCode("1");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()*3),
                            new Double("0.00")
                            });
                        }
                        
                        if(l.getEnvase().equals("CON ENVASE")&&(l.getTipo().trim().equals("5")))
                        {
                            ProductInfoExt p = getProductInfoByCode("1");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()),
                            new Double("0.00")
                            });
                            
                            p = getProductInfoByCode("4");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()),
                            new Double("0.00")
                            });
                        }
                        
                        if(l.getEnvase().equals("CON ENVASE")&&(l.getTipo().trim().equals("6")))
                        {
                            ProductInfoExt p = getProductInfoByCode("1");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()*2),
                            new Double("0.00")
                            });
                            
                            p = getProductInfoByCode("4");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()),
                            new Double("0.00")
                            });
                        }
                        
                        if(l.getEnvase().equals("CON ENVASE")&&(l.getTipo().trim().equals("7")))
                        {
                            ProductInfoExt p = getProductInfoByCode("1");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()*3),
                            new Double("0.00")
                            });
                            
                            p = getProductInfoByCode("4");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()),
                            new Double("0.00")
                            });
                        }
                        
                        if(l.getEnvase().equals("CON ENVASE")&&(l.getTipo().trim().equals("8")))
                        {
                            ProductInfoExt p = getProductInfoByCode("4");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()*2),
                            new Double("0.00")
                            });
                        }
                        
                        if(l.getEnvase().equals("CON ENVASE")&&(l.getTipo().trim().equals("9")))
                        {
                            ProductInfoExt p = getProductInfoByCode("4");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()*2),
                            new Double("0.00")
                            });
                            
                            p = getProductInfoByCode("1");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()),
                            new Double("0.00")
                            });
                        }
                        
                        if(l.getEnvase().equals("CON ENVASE")&&(
                                    l.getTipo().trim().equals("10")
                                ||l.getTipo().trim().equals("11")
                                ||l.getTipo().trim().equals("12")
                                ||l.getTipo().trim().equals("13")
                                ||l.getTipo().trim().equals("14")
                                ||l.getTipo().trim().equals("15")
                                ||l.getTipo().trim().equals("16")
                                ||l.getTipo().trim().equals("17")
                                ||l.getTipo().trim().equals("18")
                                ||l.getTipo().trim().equals("19")
                                        ))
                        {
                            ProductInfoExt p = getProductInfoByCode("19");
                            getStockDiaryInsert().exec(new Object[] {
                            UUID.randomUUID().toString(),
                            ticket.getDate(),
                            l.getMultiply() < 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            p.getID(),
                            l.getProductAttSetInstId(),
                            new Double(-l.getMultiply()),
                            new Double("0.00")
                            });
                        }*/
                    }
                }

                SentenceExec paymentinsert = new PreparedSentence(s
                    , "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, TRANSID, RETURNMSG) VALUES (?, ?, ?, ?, ?, ?)"
                    , SerializerWriteParams.INSTANCE);
                for (final PaymentInfo p : ticket.getPayments()) {
                    paymentinsert.exec(new DataParams() { public void writeValues() throws BasicException {
                        setString(1, UUID.randomUUID().toString());
                        setString(2, ticket.getId());
                        setString(3, p.getName());
                        setDouble(4, p.getTotal());
                        setString(5, ticket.getTransactionID());
                        setBytes(6, (byte[]) Formats.BYTEA.parseValue(ticket.getReturnMessage()));
                    }});

                    if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {

                        // udate customer fields...
                        ticket.getCustomer().updateCurDebt(p.getTotal(), ticket.getDate());

                        // save customer fields...
                        getDebtUpdate().exec(new DataParams() { public void writeValues() throws BasicException {
                            setDouble(1, ticket.getCustomer().getCurdebt());
                            setTimestamp(2, ticket.getCustomer().getCurdate());
                            setString(3, ticket.getCustomer().getId());
                        }});
                    }
                }

                SentenceExec taxlinesinsert = new PreparedSentence(s
                        , "INSERT INTO TAXLINES (ID, RECEIPT, TAXID, BASE, AMOUNT)  VALUES (?, ?, ?, ?, ?)"
                        , SerializerWriteParams.INSTANCE);
                if (ticket.getTaxes() != null) {
                    for (final TicketTaxInfo tickettax: ticket.getTaxes()) {
                        taxlinesinsert.exec(new DataParams() { public void writeValues() throws BasicException {
                            setString(1, UUID.randomUUID().toString());
                            setString(2, ticket.getId());
                            setString(3, tickettax.getTaxInfo().getId());
                            setDouble(4, tickettax.getSubTotal());
                            setDouble(5, tickettax.getTax());
                        }});
                    }
                }

                return null;
            }
        };
        t.execute();
    }

    public final void deleteTicket(final TicketInfo ticket, final String location) throws BasicException {

        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {

                // update the inventory
                Date d = new Date();
                for (int i = 0; i < ticket.getLinesCount(); i++) {
                    if (ticket.getLine(i).getProductID() != null)  {
                        // Hay que actualizar el stock si el hay producto
                        getStockDiaryInsert().exec( new Object[] {
                            UUID.randomUUID().toString(),
                            d,
                            ticket.getLine(i).getMultiply() >= 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            ticket.getLine(i).getProductID(),
                            ticket.getLine(i).getProductAttSetInstId(),
                            new Double(ticket.getLine(i).getMultiply()),
                            new Double(ticket.getLine(i).getPrice())
                        });
                    }
                }

                // update customer debts
                for (PaymentInfo p : ticket.getPayments()) {
                    if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {

                        // udate customer fields...
                        ticket.getCustomer().updateCurDebt(-p.getTotal(), ticket.getDate());

                         // save customer fields...
                        getDebtUpdate().exec(new DataParams() { public void writeValues() throws BasicException {
                            setDouble(1, ticket.getCustomer().getCurdebt());
                            setTimestamp(2, ticket.getCustomer().getCurdate());
                            setString(3, ticket.getCustomer().getId());
                        }});
                    }
                }

                // and delete the receipt
                new StaticSentence(s
                    , "DELETE FROM TAXLINES WHERE RECEIPT = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s
                    , "DELETE FROM PAYMENTS WHERE RECEIPT = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s
                    , "DELETE FROM TICKETLINES WHERE TICKET = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s
                    , "DELETE FROM TICKETS WHERE ID = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s
                    , "DELETE FROM RECEIPTS WHERE ID = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                return null;
            }
        };
        t.execute();
    }

    public final void deleteTicket2(final TicketInfo ticket, final String location) throws BasicException {

        Transaction t = new Transaction(s) {
            public Object transact() throws BasicException {

                // update the inventory
                /*Date d = new Date();
                for (int i = 0; i < ticket.getLinesCount(); i++) {
                    if (ticket.getLine(i).getProductID() != null)  {
                        // Hay que actualizar el stock si el hay producto
                        getStockDiaryInsert().exec( new Object[] {
                            UUID.randomUUID().toString(),
                            d,
                            ticket.getLine(i).getMultiply() >= 0.0
                                ? MovementReason.IN_REFUND.getKey()
                                : MovementReason.OUT_SALE.getKey(),
                            location,
                            ticket.getLine(i).getProductID(),
                            ticket.getLine(i).getProductAttSetInstId(),
                            new Double(ticket.getLine(i).getMultiply()),
                            new Double(ticket.getLine(i).getPrice())
                        });
                    }
                }

                // update customer debts
                for (PaymentInfo p : ticket.getPayments()) {
                    if ("debt".equals(p.getName()) || "debtpaid".equals(p.getName())) {

                        // udate customer fields...
                        ticket.getCustomer().updateCurDebt(-p.getTotal(), ticket.getDate());

                         // save customer fields...
                        getDebtUpdate().exec(new DataParams() { public void writeValues() throws BasicException {
                            setDouble(1, ticket.getCustomer().getCurdebt());
                            setTimestamp(2, ticket.getCustomer().getCurdate());
                            setString(3, ticket.getCustomer().getId());
                        }});
                    }
                }*/

                // and delete the receipt
                new StaticSentence(s
                    , "DELETE FROM TAXLINES WHERE RECEIPT = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s
                    , "DELETE FROM PAYMENTS WHERE RECEIPT = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s
                    , "DELETE FROM TICKETLINES WHERE TICKET = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s
                    , "DELETE FROM TICKETS WHERE ID = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                new StaticSentence(s
                    , "DELETE FROM RECEIPTS WHERE ID = ?"
                    , SerializerWriteString.INSTANCE).exec(ticket.getId());
                return null;
            }
        };
        t.execute();
    }
    
    public final Integer getNextTicketIndex() throws BasicException {
        return (Integer) s.DB.getSequenceSentence(s, "TICKETSNUM").find();
    }
    
    public final Integer getNextTicketRefundIndex() throws BasicException {
        return (Integer) s.DB.getSequenceSentence(s, "TICKETSNUM_REFUND").find();
    }

    public final Integer getNextTicketPaymentIndex() throws BasicException {
        return (Integer) s.DB.getSequenceSentence(s, "TICKETSNUM_PAYMENT").find();
    }

    public final SentenceList getProductCatQBF() {
        return new StaticSentence(s
            , new QBFBuilder(
                "SELECT P.ID, P.REFERENCE, P.CODE, P.NAME, P.ISCOM, P.ISSCALE, P.PRICEBUY, P.PRICESELL, P.CATEGORY, P.TAXCAT, P.ATTRIBUTESET_ID, P.IMAGE, P.STOCKCOST, P.STOCKVOLUME, CASE WHEN C.PRODUCT IS NULL THEN " + s.DB.FALSE() + " ELSE " + s.DB.TRUE() + " END, C.CATORDER, P.ATTRIBUTES, P.PRICESELL1, P.PRICESELL2, P.PRICESELL3, P.PRICESELL4,P.PRICESELL5,P.PRICESELL6,P.PRICESELL7,P.PRICESELL8,P.PRICESELL9,P.PRICESELL10,P.PRICESELL11,P.PRICESELL12,P.PRICESELL13,P.PRICESELL14,P.PRICESELL15,P.PRICESELL16,P.PRICESELL17,P.PRICESELL18,P.PRICESELL19,P.PRICESELL20,P.PRICESELL21,P.PRICESELL22,P.PRICESELL23,P.PRICESELL24,P.PRICESELL25,P.PRICESELL26,P.PRICESELL27,P.PRICESELL28,P.PRICESELL29,P.PRICESELL30,P.PRICESELL31,P.PRICESELL32,P.PRICESELL33,P.PRICESELL34,P.PRICESELL35,P.PRICESELL36,P.PRICESELL37,P.PRICESELL38,P.PRICESELL39,P.PRICESELL40,P.PRICESELL41,P.PRICESELL42,P.PRICESELL43,P.PRICEBUY1,P.PRICEBUY2,P.PRICEBUY3,P.PRICEBUY4,PRICEBUY5,PRICEBUY6,PRICEBUY7,PRICEBUY8,PRICEBUY9,PRICEBUY10,PRICEBUY11,PRICEBUY12,PRICEBUY13,PRICEBUY14,PRICEBUY15,PRICEBUY16,PRICEBUY17,PRICEBUY18,PRICEBUY19,PRICEBUY20,PRICEBUY21,PRICEBUY22,PRICEBUY1S,PRICEBUY2S,PRICEBUY3S,PRICEBUY4S,PRICEBUY5S,PRICEBUY6S,PRICEBUY7S,PRICEBUY8S,PRICEBUY9S,PRICEBUY10S,PRICEBUY11S,PRICEBUY12S,PRICEBUY13S,PRICEBUY14S,PRICEBUY15S,PRICEBUY16S,PRICEBUY17S,PRICEBUY18S,PRICEBUY19S,PRICEBUY20S,PRICEBUY21S,PRICEBUY22S " +
                "FROM PRODUCTS P LEFT OUTER JOIN PRODUCTS_CAT C ON P.ID = C.PRODUCT " +
                "WHERE ?(QBF_FILTER) " +
                "ORDER BY P.REFERENCE", new String[] {"P.NAME", "P.PRICEBUY", "P.PRICESELL", "P.CATEGORY", "P.CODE"})
            , new SerializerWriteBasic(new Datas[] {Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.DOUBLE, Datas.OBJECT, Datas.STRING, Datas.OBJECT, Datas.STRING})
            , productsRow.getSerializerRead());
    }

    public final SentenceExec getProductCatInsert() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                Object[] values = (Object[]) params;
                int i = new PreparedSentence(s
                    , "INSERT INTO PRODUCTS (ID, REFERENCE, CODE, NAME, ISCOM, ISSCALE, PRICEBUY, PRICESELL, CATEGORY, TAXCAT, ATTRIBUTESET_ID, IMAGE, STOCKCOST, STOCKVOLUME, ATTRIBUTES,PRICESELL1,PRICESELL2,PRICESELL3,PRICESELL4,"
                        +"PRICESELL5,PRICESELL6,PRICESELL7,PRICESELL8,PRICESELL9,PRICESELL10,PRICESELL11,PRICESELL12,PRICESELL13,PRICESELL14,PRICESELL15,PRICESELL16,PRICESELL17,PRICESELL18,PRICESELL19,PRICESELL20,PRICESELL21,PRICESELL22,PRICESELL23,PRICESELL24,PRICESELL25,PRICESELL26,PRICESELL27,PRICESELL28,PRICESELL29,PRICESELL30,PRICESELL31,PRICESELL32,PRICESELL33,PRICESELL34,PRICESELL35,PRICESELL36,PRICESELL37,PRICESELL38,PRICESELL39,PRICESELL40,PRICESELL41,PRICESELL42,PRICESELL43,PRICEBUY1,PRICEBUY2,PRICEBUY3,PRICEBUY4,PRICEBUY5,PRICEBUY6,PRICEBUY7,PRICEBUY8,PRICEBUY9,PRICEBUY10,PRICEBUY11,PRICEBUY12,PRICEBUY13,PRICEBUY14,PRICEBUY15,PRICEBUY16,PRICEBUY17,PRICEBUY18,PRICEBUY19,PRICEBUY20,PRICEBUY21,PRICEBUY22,PRICEBUY1S,PRICEBUY2S,PRICEBUY3S,PRICEBUY4S,PRICEBUY5S,PRICEBUY6S,PRICEBUY7S,PRICEBUY8S,PRICEBUY9S,PRICEBUY10S,PRICEBUY11S,PRICEBUY12S,PRICEBUY13S,PRICEBUY14S,PRICEBUY15S,PRICEBUY16S,PRICEBUY17S,PRICEBUY18S,PRICEBUY19S,PRICEBUY20S,PRICEBUY21S,PRICEBUY22S) "
                        +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,	?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
                    , new SerializerWriteBasicExt(productsRow.getDatas(), new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 19, 20,21,	22,	23,	24,	25,	26,	27,	28,	29,	30,	31,	32,	33,	34,	35,	36,	37,	38,	39,	40,	41,	42,	43,	44,	45,	46,	47,	48,	49,	50,	51,	52,	53,	54,	55,	56,	57,	58,	59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103})).exec(params);
                if (i > 0 && ((Boolean)values[14]).booleanValue()) {
                    return new PreparedSentence(s
                        , "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)"
                        , new SerializerWriteBasicExt(productsRow.getDatas(), new int[] {0, 15})).exec(params);
                } else {
                    return i;
                }
            }
        };
    }

    public final SentenceExec getProductCatUpdate() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                Object[] values = (Object[]) params;
                int i = new PreparedSentence(s
                    , "UPDATE PRODUCTS SET ID = ?, REFERENCE = ?, CODE = ?, NAME = ?, ISCOM = ?, ISSCALE = ?, PRICEBUY = ?, PRICESELL = ?, CATEGORY = ?, TAXCAT = ?, ATTRIBUTESET_ID = ?, IMAGE = ?, STOCKCOST = ?, STOCKVOLUME = ?, ATTRIBUTES = ?,PRICESELL1=?,PRICESELL2=?,PRICESELL3=?,PRICESELL4=?,PRICESELL5=?,PRICESELL6=?,PRICESELL7=?,PRICESELL8=?,PRICESELL9=?,PRICESELL10=?,PRICESELL11=?,PRICESELL12=?,PRICESELL13=?,PRICESELL14=?,PRICESELL15=?,PRICESELL16=?,PRICESELL17=?,PRICESELL18=?,PRICESELL19=?,PRICESELL20=?,PRICESELL21=?,PRICESELL22=?,PRICESELL23=?,PRICESELL24=?,PRICESELL25=?,PRICESELL26=?,PRICESELL27=?,PRICESELL28=?,PRICESELL29=?,PRICESELL30=?,PRICESELL31=?,PRICESELL32=?,PRICESELL33=?,PRICESELL34=?,PRICESELL35=?,PRICESELL36=?,PRICESELL37=?,PRICESELL38=?,PRICESELL39=?,PRICESELL40=?,PRICESELL41=?,PRICESELL42=?,PRICESELL43=?,PRICEBUY1=?,PRICEBUY2=?,PRICEBUY3=?,PRICEBUY4=?,PRICEBUY5=?,PRICEBUY6=?,PRICEBUY7=?,PRICEBUY8=?,PRICEBUY9=?,PRICEBUY10=?,PRICEBUY11=?,PRICEBUY12=?,PRICEBUY13=?,PRICEBUY14=?,PRICEBUY15=?,PRICEBUY16=?,PRICEBUY17=?,PRICEBUY18=?,PRICEBUY19=?,PRICEBUY20=?,PRICEBUY21=?,PRICEBUY22=?,PRICEBUY1S=?,PRICEBUY2S=?,PRICEBUY3S=?,PRICEBUY4S=?,PRICEBUY5S=?,PRICEBUY6S=?,PRICEBUY7S=?,PRICEBUY8S=?,PRICEBUY9S=?,PRICEBUY10S=?,PRICEBUY11S=?,PRICEBUY12S=?,PRICEBUY13S=?,PRICEBUY14S=?,PRICEBUY15S=?,PRICEBUY16S=?,PRICEBUY17S=?,PRICEBUY18S=?,PRICEBUY19S=?,PRICEBUY20S=?,PRICEBUY21S=?,PRICEBUY22S=? WHERE ID = ?"
                    , new SerializerWriteBasicExt(productsRow.getDatas(), new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16,17,18,19,20,21,	22,	23,	24,	25,	26,	27,	28,	29,	30,	31,	32,	33,	34,	35,	36,	37,	38,	39,	40,	41,	42,	43,	44,	45,	46,	47,	48,	49,	50,	51,	52,	53,	54,	55,	56,	57,	58,	59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103, 0})).exec(params);
                if (i > 0) {
                    if (((Boolean)values[14]).booleanValue()) {
                        if (new PreparedSentence(s
                                , "UPDATE PRODUCTS_CAT SET CATORDER = ? WHERE PRODUCT = ?"
                                , new SerializerWriteBasicExt(productsRow.getDatas(), new int[] {15, 0})).exec(params) == 0) {
                            new PreparedSentence(s
                                , "INSERT INTO PRODUCTS_CAT (PRODUCT, CATORDER) VALUES (?, ?)"
                                , new SerializerWriteBasicExt(productsRow.getDatas(), new int[] {0, 15})).exec(params);
                        }
                    } else {
                        new PreparedSentence(s
                            , "DELETE FROM PRODUCTS_CAT WHERE PRODUCT = ?"
                            , new SerializerWriteBasicExt(productsRow.getDatas(), new int[] {0})).exec(params);
                    }
                }
                return i;
            }
        };
    }

    public final SentenceExec getProductCatDelete() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                new PreparedSentence(s
                    , "DELETE FROM PRODUCTS_CAT WHERE PRODUCT = ?"
                    , new SerializerWriteBasicExt(productsRow.getDatas(), new int[] {0})).exec(params);
                return new PreparedSentence(s
                    , "DELETE FROM PRODUCTS WHERE ID = ?"
                    , new SerializerWriteBasicExt(productsRow.getDatas(), new int[] {0})).exec(params);
            }
        };
    }

    public final SentenceExec getDebtUpdate() {

        return new PreparedSentence(s
                , "UPDATE CUSTOMERS SET CURDEBT = ?, CURDATE = ? WHERE ID = ?"
                , SerializerWriteParams.INSTANCE);
    }

    public final SentenceExec getStockDiaryInsert() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                /*int updateresult = ((Object[]) params)[5] == null // si ATTRIBUTESETINSTANCE_ID is null
                    ? new PreparedSentence(s
                        , "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE LOCATION = ? AND PRODUCT = ? AND ATTRIBUTESETINSTANCE_ID IS NULL"
                        , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {6,3, 4})).exec(params)
                    : new PreparedSentence(s
                        , "UPDATE STOCKCURRENT SET UNITS = (UNITS + ?) WHERE LOCATION = ? AND PRODUCT = ? AND ATTRIBUTESETINSTANCE_ID = ?"
                        , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {6, 3, 4, 5})).exec(params);

                
                if (updateresult == 0) {
                    new PreparedSentence(s
                        , "INSERT INTO STOCKCURRENT (LOCATION, PRODUCT, ATTRIBUTESETINSTANCE_ID, UNITS) VALUES (?, ?, ?, ?)"
                        , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {3, 4, 5, 6})).exec(params);
                }*/
                new PreparedSentence(s
                    , "INSERT INTO STOCKDIARYTEMP (ID, DATENEW, REASON, LOCATION, PRODUCT, ATTRIBUTESETINSTANCE_ID, UNITS, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                    , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {0, 1, 2, 3, 4, 5, 6, 7})).exec(params);
                return new PreparedSentence(s
                    , "INSERT INTO STOCKDIARY (ID, DATENEW, REASON, LOCATION, PRODUCT, ATTRIBUTESETINSTANCE_ID, UNITS, PRICE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                    , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {0, 1, 2, 3, 4, 5, 6, 7})).exec(params);
            }
        };
    }

    public final SentenceExec getStockDiaryDelete() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                int updateresult = ((Object[]) params)[5] == null // if ATTRIBUTESETINSTANCE_ID is null
                        ? new PreparedSentence(s
                            , "UPDATE STOCKCURRENT SET UNITS = (UNITS - ?) WHERE LOCATION = ? AND PRODUCT = ? AND ATTRIBUTESETINSTANCE_ID IS NULL"
                            , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {6, 3, 4})).exec(params)
                        : new PreparedSentence(s
                            , "UPDATE STOCKCURRENT SET UNITS = (UNITS - ?) WHERE LOCATION = ? AND PRODUCT = ? AND ATTRIBUTESETINSTANCE_ID = ?"
                            , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {6, 3, 4, 5})).exec(params);

                if (updateresult == 0) {
                    new PreparedSentence(s
                        , "INSERT INTO STOCKCURRENT (LOCATION, PRODUCT, ATTRIBUTESETINSTANCE_ID, UNITS) VALUES (?, ?, ?, -(?))"
                        , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {3, 4, 5, 6})).exec(params);
                }
                return new PreparedSentence(s
                    , "DELETE FROM STOCKDIARY WHERE ID = ?"
                    , new SerializerWriteBasicExt(stockdiaryDatas, new int[] {0})).exec(params);
            }
        };
    }

    public final SentenceExec getPaymentMovementInsert() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                new PreparedSentence(s
                    , "INSERT INTO RECEIPTS (ID, MONEY, DATENEW) VALUES (?, ?, ?)"
                    , new SerializerWriteBasicExt(paymenttabledatas, new int[] {0, 1, 2})).exec(params);
                return new PreparedSentence(s
                    , "INSERT INTO PAYMENTS (ID, RECEIPT, PAYMENT, TOTAL, NOTES) VALUES (?, ?, ?, ?, ?)"
                    , new SerializerWriteBasicExt(paymenttabledatas, new int[] {3, 0, 4, 5,6})).exec(params);
            }
        };
    }

    public final SentenceExec getPaymentMovementDelete() {
        return new SentenceExecTransaction(s) {
            public int execInTransaction(Object params) throws BasicException {
                new PreparedSentence(s
                    , "DELETE FROM PAYMENTS WHERE ID = ?"
                    , new SerializerWriteBasicExt(paymenttabledatas, new int[] {3})).exec(params);
                return new PreparedSentence(s
                    , "DELETE FROM RECEIPTS WHERE ID = ?"
                    , new SerializerWriteBasicExt(paymenttabledatas, new int[] {0})).exec(params);
            }
        };
    }

    public final double findProductStock(String warehouse, String id, String attsetinstid) throws BasicException {

        PreparedSentence p = attsetinstid == null
                ? new PreparedSentence(s, "SELECT UNITS FROM STOCKCURRENT WHERE LOCATION = ? AND PRODUCT = ? AND ATTRIBUTESETINSTANCE_ID IS NULL"
                    , new SerializerWriteBasic(Datas.STRING, Datas.STRING)
                    , SerializerReadDouble.INSTANCE)
                : new PreparedSentence(s, "SELECT UNITS FROM STOCKCURRENT WHERE LOCATION = ? AND PRODUCT = ? AND ATTRIBUTESETINSTANCE_ID = ?"
                    , new SerializerWriteBasic(Datas.STRING, Datas.STRING, Datas.STRING)
                    , SerializerReadDouble.INSTANCE);

        Double d = (Double) p.find(warehouse, id, attsetinstid);
        return d == null ? 0.0 : d.doubleValue();
    }

    public final SentenceExec getCatalogCategoryAdd() {
        return new StaticSentence(s
                , "INSERT INTO PRODUCTS_CAT(PRODUCT, CATORDER) SELECT ID, " + s.DB.INTEGER_NULL() + " FROM PRODUCTS WHERE CATEGORY = ?"
                , SerializerWriteString.INSTANCE);
    }
    public final SentenceExec getCatalogCategoryDel() {
        return new StaticSentence(s
                , "DELETE FROM PRODUCTS_CAT WHERE PRODUCT = ANY (SELECT ID FROM PRODUCTS WHERE CATEGORY = ?)"
                , SerializerWriteString.INSTANCE);
    }

    public final TableDefinition getTableCategories() {
        return new TableDefinition(s,
            "CATEGORIES"
            , new String[] {"ID", "NAME", "PARENTID", "IMAGE"}
            , new String[] {"ID", AppLocal.getIntString("Label.Name"), "", AppLocal.getIntString("label.image")}
            , new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.IMAGE}
            , new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.NULL}
            , new int[] {0}
        );
    }
    public final TableDefinition getTableTaxes() {
        return new TableDefinition(s,
            "TAXES"
            , new String[] {"ID", "NAME", "CATEGORY", "VALIDFROM", "CUSTCATEGORY", "PARENTID", "RATE", "RATECASCADE", "RATEORDER"}
            , new String[] {"ID", AppLocal.getIntString("Label.Name"), AppLocal.getIntString("label.taxcategory"), AppLocal.getIntString("Label.ValidFrom"), AppLocal.getIntString("label.custtaxcategory"), AppLocal.getIntString("label.taxparent"), AppLocal.getIntString("label.dutyrate"), AppLocal.getIntString("label.cascade"), AppLocal.getIntString("label.order")}
            , new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.BOOLEAN, Datas.INT}
            , new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING, Formats.TIMESTAMP, Formats.STRING, Formats.STRING, Formats.PERCENT, Formats.BOOLEAN, Formats.INT}
            , new int[] {0}
        );
    }

    public final TableDefinition getTableTaxCustCategories() {
        return new TableDefinition(s,
            "TAXCUSTCATEGORIES"
            , new String[] {"ID", "NAME"}
            , new String[] {"ID", AppLocal.getIntString("Label.Name")}
            , new Datas[] {Datas.STRING, Datas.STRING}
            , new Formats[] {Formats.STRING, Formats.STRING}
            , new int[] {0}
        );
    }
    public final TableDefinition getTableTaxCategories() {
        return new TableDefinition(s,
            "TAXCATEGORIES"
            , new String[] {"ID", "NAME"}
            , new String[] {"ID", AppLocal.getIntString("Label.Name")}
            , new Datas[] {Datas.STRING, Datas.STRING}
            , new Formats[] {Formats.STRING, Formats.STRING}
            , new int[] {0}
        );
    }

    public final TableDefinition getTableLocations() {
        return new TableDefinition(s,
            "LOCATIONS"
            , new String[] {"ID", "NAME", "ADDRESS"}
            , new String[] {"ID", AppLocal.getIntString("label.locationname"), AppLocal.getIntString("label.locationaddress")}
            , new Datas[] {Datas.STRING, Datas.STRING, Datas.STRING}
            , new Formats[] {Formats.STRING, Formats.STRING, Formats.STRING}
            , new int[] {0}
        );
    }

    protected static class CustomerExtRead implements SerializerRead {
        public Object readValues(DataRead dr) throws BasicException {
            CustomerInfoExt c = new CustomerInfoExt(dr.getString(1));
            c.setTaxid(dr.getString(2));
            c.setSearchkey(dr.getString(3));
            c.setName(dr.getString(4));
            c.setCard(dr.getString(5));
            c.setTaxCustomerID(dr.getString(6));
            c.setNotes(dr.getString(7));
            c.setMaxdebt(dr.getDouble(8));
            c.setVisible(dr.getBoolean(9).booleanValue());
            c.setCurdate(dr.getTimestamp(10));
            c.setCurdebt(dr.getDouble(11));
            c.setFirstname(dr.getString(12));
            c.setLastname(dr.getString(13));
            c.setEmail(dr.getString(14));
            c.setPhone(dr.getString(15));
            c.setPhone2(dr.getString(16));
            c.setFax(dr.getString(17));
            c.setAddress(dr.getString(18));
            c.setAddress2(dr.getString(19));
            c.setPostal(dr.getString(20));
            c.setCity(dr.getString(21));
            c.setRegion(dr.getString(22));
            c.setCountry(dr.getString(23));
            return c;
        }
    }
}
