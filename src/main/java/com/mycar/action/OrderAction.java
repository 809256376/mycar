package com.mycar.action;

import com.mycar.model.Order;
import com.mycar.service.OrderService;
import com.mycar.utils.HttpResponse;
import com.mycar.utils.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by stupid-coder on 7/17/17.
 */

@RestController
public class OrderAction {

    private static Logger logger = LoggerFactory.getLogger(OrderAction.class);

    @Resource
    OrderService orderService;

    @RequestMapping(value = "/order/{status}/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse select(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("status") int status)
    {
        List<Order> orderList = orderService.getOrdersByStatus(status);
        if ( orderList == null ) logger.error("failure to get the orders by status - status:{}", status);
        return new HttpResponse(orderList);
    }

    @RequestMapping(value = "/order/{viid}/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse insert(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("viid") long viid,
                               @RequestBody Order order)
    {
        int status = orderService.insertOrder(viid,order);
        if ( status < 0 ) {
            logger.error("failure to insert the order - viid:{}\torder:{}",viid, order);
            return new HttpResponse(status);
        } else {
            logger.info("[insert][order:{}]",order);
            return new HttpResponse(order.getId());
        }
    }

    @RequestMapping(value = "/order/check/{oid}/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse check(HttpServletRequest request,
                              HttpServletResponse response,
                              @PathVariable("oid") long oid)
    {
        return new HttpResponse(orderService.checkOrder(oid));
    }

    @RequestMapping(value = "/order/renting/{oid}/{number}/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse rent(HttpServletRequest request,
                             HttpServletResponse response,
                             @PathVariable("oid") long id,
                             @PathVariable("number") String number,
                             @RequestBody Order order)
    {
        return new HttpResponse(orderService.rentOrder(id,order,number));
    }


    @RequestMapping(value = "/order/drawback/{oid}/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse drawback(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @PathVariable("oid") long id,
                                 @RequestBody Order order)
    {
        return new HttpResponse(orderService.drawBackOrder(id,order));
    }

    @RequestMapping(value = "/order/finished/{oid}/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse finished(HttpServletRequest request,
                                 HttpServletResponse response,
                                 @PathVariable("oid") long id,
                                 @RequestBody Order order)
    {
        return new HttpResponse(orderService.finishedOrder(id,order));
    }

    @RequestMapping(value = "/order/cancle/{oid}/", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpResponse cancle(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("oid") long id)
    {
        return new HttpResponse(orderService.cancleOrder(id));
    }
}
