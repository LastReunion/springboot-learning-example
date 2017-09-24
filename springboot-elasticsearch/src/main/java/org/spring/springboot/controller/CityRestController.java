package org.spring.springboot.controller;

import org.spring.springboot.domain.City;
import org.spring.springboot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 城市 Controller 实现 Restful HTTP 服务
 * <p>
 * Created by bysocket on 03/05/2017.
 */
@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    /**
     * 新增城市信息
     * POST http://127.0.0.1:8080/api/city
     {
         "id":"1",
         "provinceid":"1",
         "cityname":"温岭",
         "description":"温岭是个好城市"
     }
     POST http://127.0.0.1:8080/api/city
     {
         "id":"2",
         "provinceid":"2",
         "cityname":"温州",
         "description":"温州是个热城市"
     }
     *
     *在「数据浏览」tab，可以查阅到 ES 中数据是否被插入，插入后的数据格式如下：
     {
         "_index": "cityindex",
         "_type": "city",
         "_id": "1",
         "_version": 1,
         "_score": 1,
         "_source":
        {
             "id": 1,
             "provinceid": 1,
             "cityname": "温岭",
             "description": "温岭是个好城市"
        }
     }
     *
     *
     * @param city
     * @return
     */
    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public Long createCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }


    /**
     * GET http://localhost:8080/api/city/search?pageNumber=0&pageSize=10&searchContent=温岭
     [
         {
             "id": 1,
             "provinceid": 1,
             "cityname": "温岭",
             "description": "温岭是个好城市"
         },
         {
             "id": 2,
             "provinceid": 2,
             "cityname": "温州",
             "description": "温州是个热城市"
         }
     ]
     *
     * 从启动后台 Console 可以看出，打印出来对应的 DSL 语句：
     {
     "function_score" : {
        "functions" : [ {
            "filter" : {
                "bool" : {
                    "should" : {
                        "match" : {
                            "cityname" : {
                                "query" : "温岭",
                                "type" : "boolean"
                            }
                        }
                    }
                }
            },
            "weight" : 1000.0
            },

            {
            "filter" : {
                "bool" : {
                    "should" : {
                        "match" : {
                            "description" : {
                                 "query" : "温岭",
                                 "type" : "boolean"
                            }
                        }
                    }
                }
            },
            "weight" : 100.0
            } ]
        }
     }
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     *
     * 先创建分页参数，然后用 FunctionScoreQueryBuilder 定义 Function Score Query，
     * 并设置对应字段的权重分值。城市名称 1000 分，description 100 分。
     * 然后创建该搜索的 DSL 查询，并打印出来。
     */
    @RequestMapping(value = "/api/city/search", method = RequestMethod.GET)
    public List<City> searchCity(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return cityService.searchCity(pageNumber,pageSize,searchContent);
    }
}
