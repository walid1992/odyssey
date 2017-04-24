package com.syswin.toon.app.home.ui.page;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.syswin.toon.app.home.bean.server.NearbyBean;
import com.syswin.toon.app.home.constants.EnumConst;
import com.syswin.toon.lib.martian.mvp.IModel;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Author   : walid
 * Data     : 2016-09-18  17:10
 * Describe :
 */

class NearbyModel implements IModel {

    private int type;
    private String estateDatas = "[{\"uid\":\"116946\",\"latitudeLongitude\":\"39.995969,116.453696\",\"typeCode\":\"110000\",\"id\":\"110000_116946\",\"pictureUrl\":\"http://scloud.toon.mobi/f/mi7BtINdqnZXRIqIABsmTguagEwf2n4IsoOsDmXaMEMfN_106_106_91_0.jpg\",\"address\":\"G45大广高速辅路附近\",\"name\":\"桐城公园\",\"distance\":0.34322751560816966},{\"uid\":\"115336\",\"latitudeLongitude\":\"39.995108,116.451893\",\"typeCode\":\"110000\",\"id\":\"110000_115336\",\"pictureUrl\":\"http://scloud.toon.mobi/f/9+ox-v3v1ftpWwdh-HUxjqn2rzTMDGIlMSN+4Z1NTQQfN_106_106_91_0.jpg\",\"address\":\"渠西路220号\",\"name\":\"望承公园\",\"distance\":0.5146542143319837},{\"uid\":\"115316\",\"latitudeLongitude\":\"40.000173,116.452691\",\"typeCode\":\"110000\",\"id\":\"110000_115316\",\"pictureUrl\":\"http://scloud.toon.mobi/f/u9IjDAHfEPz9JM92qRS7XrasNDcqlzdnf-SfFNpylnUfN_106_106_91_0.jpg\",\"address\":\"湖光桥附近\",\"name\":\"望和公园\",\"distance\":0.5934866774116175},{\"uid\":\"6980\",\"latitudeLongitude\":\"39.992916,116.468512\",\"typeCode\":\"110000\",\"id\":\"110000_6980\",\"pictureUrl\":\"http://scloud.toon.mobi/f/NgdinMbABSlN5Y8KuojoC9kNQwSzLs+CgIu0wdSyyDkfN_106_106_91_0.jpg\",\"address\":\"北京朝阳广顺北大街33号凯德MALL望京4层04-06\",\"name\":\"泡泡秀体验馆\",\"distance\":1.002953078497429},{\"uid\":\"96648\",\"latitudeLongitude\":\"40.005199,116.45474\",\"typeCode\":\"110000\",\"id\":\"110000_96648\",\"pictureUrl\":\"http://scloud.toon.mobi/f/SdSAlnV44zGi6oAaxMEU9ShHYRxBJiMKl2QjprLNkX8fF_106_106_91_0.png\",\"address\":\"师家坟村156号\",\"name\":\"北京市朝阳区北小河公园\",\"distance\":1.0050357331865207},{\"uid\":\"7045\",\"latitudeLongitude\":\"40.005297,116.454962\",\"typeCode\":\"110000\",\"id\":\"110000_7045\",\"pictureUrl\":\"http://scloud.toon.mobi/f/0ayjIE0YOezkLfdHasULGp461+0ZvrcxsAPqXaU51vAfN_106_106_91_0.jpg\",\"address\":\"朝阳区师家坟156号\",\"name\":\"北小河公园\",\"distance\":1.0111014886370802},{\"uid\":\"112165\",\"latitudeLongitude\":\"40.001973,116.447984\",\"typeCode\":\"110000\",\"id\":\"110000_112165\",\"pictureUrl\":\"http://scloud.toon.mobi/f/M3NNBSr6XYeEk5LB337YswnI0DGg850XAKLaOaVLcZMfN_106_106_91_0.jpg\",\"address\":\"鼎成路2号\",\"name\":\"护国天仙圣母庙\",\"distance\":1.0293486412865338},{\"uid\":\"96690\",\"latitudeLongitude\":\"39.988301,116.451495\",\"typeCode\":\"110000\",\"id\":\"110000_96690\",\"pictureUrl\":\"http://scloud.toon.mobi/f/08k2RLdIw2Vpep+A6DTROb8lvO87pfff31pnztpkKAofN_106_106_91_0.jpg\",\"address\":\"望京西路\",\"name\":\"北京龙韵国际公园\",\"distance\":1.0475761271106545},{\"uid\":\"131286\",\"latitudeLongitude\":\"39.987044,116.460839\",\"typeCode\":\"110000\",\"id\":\"110000_131286\",\"pictureUrl\":\"http://scloud.toon.mobi/f/OKJbdVYaqqpm0mqL0MR56mSoGECgXZUkmvlDp4QJFmMfN_106_106_91_0.jpg\",\"address\":\"花家地南街174号附近\",\"name\":\"花家地西里文化广场\",\"distance\":1.0794942390063222},{\"uid\":\"115303\",\"latitudeLongitude\":\"40.000142,116.471056\",\"typeCode\":\"110000\",\"id\":\"110000_115303\",\"pictureUrl\":\"http://scloud.toon.mobi/f/0Y1D+zumwglsMm-BbMvmrsrrqDWymj65nFNE9-SpCSYfN_106_106_91_0.jpg\",\"address\":\"宏昌路望京西园四区北侧\",\"name\":\"望京文化广场\",\"distance\":1.2115324527719855}]";
    private String scenicDatas = "[{\"uid\":\"116946\",\"latitudeLongitude\":\"39.995969,116.453696\",\"typeCode\":\"110000\",\"id\":\"110000_116946\",\"pictureUrl\":\"http://scloud.toon.mobi/f/mi7BtINdqnZXRIqIABsmTguagEwf2n4IsoOsDmXaMEMfN_106_106_91_0.jpg\",\"address\":\"G45大广高速辅路附近\",\"name\":\"桐城公园\",\"distance\":0.34322751560816966},{\"uid\":\"115336\",\"latitudeLongitude\":\"39.995108,116.451893\",\"typeCode\":\"110000\",\"id\":\"110000_115336\",\"pictureUrl\":\"http://scloud.toon.mobi/f/9+ox-v3v1ftpWwdh-HUxjqn2rzTMDGIlMSN+4Z1NTQQfN_106_106_91_0.jpg\",\"address\":\"渠西路220号\",\"name\":\"望承公园\",\"distance\":0.5146542143319837},{\"uid\":\"115316\",\"latitudeLongitude\":\"40.000173,116.452691\",\"typeCode\":\"110000\",\"id\":\"110000_115316\",\"pictureUrl\":\"http://scloud.toon.mobi/f/u9IjDAHfEPz9JM92qRS7XrasNDcqlzdnf-SfFNpylnUfN_106_106_91_0.jpg\",\"address\":\"湖光桥附近\",\"name\":\"望和公园\",\"distance\":0.5934866774116175},{\"uid\":\"6980\",\"latitudeLongitude\":\"39.992916,116.468512\",\"typeCode\":\"110000\",\"id\":\"110000_6980\",\"pictureUrl\":\"http://scloud.toon.mobi/f/NgdinMbABSlN5Y8KuojoC9kNQwSzLs+CgIu0wdSyyDkfN_106_106_91_0.jpg\",\"address\":\"北京朝阳广顺北大街33号凯德MALL望京4层04-06\",\"name\":\"泡泡秀体验馆\",\"distance\":1.002953078497429},{\"uid\":\"96648\",\"latitudeLongitude\":\"40.005199,116.45474\",\"typeCode\":\"110000\",\"id\":\"110000_96648\",\"pictureUrl\":\"http://scloud.toon.mobi/f/SdSAlnV44zGi6oAaxMEU9ShHYRxBJiMKl2QjprLNkX8fF_106_106_91_0.png\",\"address\":\"师家坟村156号\",\"name\":\"北京市朝阳区北小河公园\",\"distance\":1.0050357331865207},{\"uid\":\"7045\",\"latitudeLongitude\":\"40.005297,116.454962\",\"typeCode\":\"110000\",\"id\":\"110000_7045\",\"pictureUrl\":\"http://scloud.toon.mobi/f/0ayjIE0YOezkLfdHasULGp461+0ZvrcxsAPqXaU51vAfN_106_106_91_0.jpg\",\"address\":\"朝阳区师家坟156号\",\"name\":\"北小河公园\",\"distance\":1.0111014886370802},{\"uid\":\"112165\",\"latitudeLongitude\":\"40.001973,116.447984\",\"typeCode\":\"110000\",\"id\":\"110000_112165\",\"pictureUrl\":\"http://scloud.toon.mobi/f/M3NNBSr6XYeEk5LB337YswnI0DGg850XAKLaOaVLcZMfN_106_106_91_0.jpg\",\"address\":\"鼎成路2号\",\"name\":\"护国天仙圣母庙\",\"distance\":1.0293486412865338},{\"uid\":\"96690\",\"latitudeLongitude\":\"39.988301,116.451495\",\"typeCode\":\"110000\",\"id\":\"110000_96690\",\"pictureUrl\":\"http://scloud.toon.mobi/f/08k2RLdIw2Vpep+A6DTROb8lvO87pfff31pnztpkKAofN_106_106_91_0.jpg\",\"address\":\"望京西路\",\"name\":\"北京龙韵国际公园\",\"distance\":1.0475761271106545},{\"uid\":\"131286\",\"latitudeLongitude\":\"39.987044,116.460839\",\"typeCode\":\"110000\",\"id\":\"110000_131286\",\"pictureUrl\":\"http://scloud.toon.mobi/f/OKJbdVYaqqpm0mqL0MR56mSoGECgXZUkmvlDp4QJFmMfN_106_106_91_0.jpg\",\"address\":\"花家地南街174号附近\",\"name\":\"花家地西里文化广场\",\"distance\":1.0794942390063222},{\"uid\":\"115303\",\"latitudeLongitude\":\"40.000142,116.471056\",\"typeCode\":\"110000\",\"id\":\"110000_115303\",\"pictureUrl\":\"http://scloud.toon.mobi/f/0Y1D+zumwglsMm-BbMvmrsrrqDWymj65nFNE9-SpCSYfN_106_106_91_0.jpg\",\"address\":\"宏昌路望京西园四区北侧\",\"name\":\"望京文化广场\",\"distance\":1.2115324527719855}]";
    private String buildingDatas = "[{\"uid\":\"2306\",\"id\":\"120200_2306\",\"pictureUrl\":\"http://scloud.toon.mobi/f/H1bIB-TaiAkb+TeKi+3YtYURpQn4gA4NjhuRN8UDqQsfN_106_106_91_0.jpg\",\"address\":\"望京湖光中路和望京西路交叉路口东南角\",\"name\":\"思源大厦\",\"latitudeLongitude\":\"39.996417,116.458206\",\"typeCode\":\"120200\",\"distance\":0.05685163523345213},{\"uid\":\"2047\",\"id\":\"120200_2047\",\"pictureUrl\":\"http://scloud.toon.mobi/f/pZJyJTNrsDbcRe07acfqffJ5osWj-QdMoDT2htMKbYUfN_106_106_91_0.jpg\",\"address\":\"望京西路41号\",\"name\":\"novo商场\",\"latitudeLongitude\":\"39.994482,116.456532\",\"typeCode\":\"120200\",\"distance\":0.23507581398946317},{\"uid\":\"11249\",\"id\":\"120200_11249\",\"pictureUrl\":\"http://scloud.toon.mobi/f/Om4ywNC9aIFPrcttDTJIy0xIkLm-gTp7Fl9UE5n7iCIfN_106_106_91_0.jpg\",\"address\":\"望京西路106号附近\",\"name\":\"望京新城(望京西路)\",\"latitudeLongitude\":\"39.997117,116.460637\",\"typeCode\":\"120200\",\"distance\":0.2740431809506682},{\"uid\":\"11251\",\"id\":\"120200_11251\",\"pictureUrl\":\"http://scloud.toon.mobi/f/HgPcDpwh3sMzczoUGbFsRodH1RP9mXXFMP1cbOZ-GT8fF_106_106_91_0.png\",\"address\":\"南湖渠三巷与望京西路交叉口北200米\",\"name\":\"望京新城K7区\",\"latitudeLongitude\":\"39.996489,116.46182\",\"typeCode\":\"120200\",\"distance\":0.36461663525952825},{\"uid\":\"2128\",\"id\":\"120200_2128\",\"pictureUrl\":\"http://scloud.toon.mobi/f/1VH8KQFQ5W0muxyhaEq5mbHFlHkxjbizWkd8bMf4fUQfN_106_106_91_0.jpg\",\"address\":\"望京西路53号\",\"name\":\"望京西路53号楼\",\"latitudeLongitude\":\"39.99149,116.456945\",\"typeCode\":\"120200\",\"distance\":0.5538518213380818},{\"uid\":\"2463\",\"id\":\"120200_2463\",\"pictureUrl\":\"http://scloud.toon.mobi/f/onRUtzloB9C0BuCZgj9KgBiMdQbs0dWkwB0qMFcyZSAfN_106_106_91_0.jpg\",\"address\":\"湖光中街甲6号\",\"name\":\"港旅大厦\",\"latitudeLongitude\":\"39.996401,116.464448\",\"typeCode\":\"120200\",\"distance\":0.5884820420209567},{\"uid\":\"34\",\"id\":\"120200_34\",\"pictureUrl\":\"http://scloud.toon.mobi/f/HgPcDpwh3sMzczoUGbFsRodH1RP9mXXFMP1cbOZ-GT8fF_106_106_91_0.png\",\"address\":\"朝阳区东北四环和京承高速交汇处\",\"name\":\"北京香颂\",\"latitudeLongitude\":\"39.992067,116.45286\",\"typeCode\":\"120200\",\"distance\":0.629659812787842},{\"uid\":\"10855\",\"id\":\"120200_10855\",\"pictureUrl\":\"http://scloud.toon.mobi/f/xQo4H98WsRFVSZbY4h9bHCazYOagPDfIJRr9alfW1LcfN_106_106_91_0.jpg\",\"address\":\"望京湖光中街1号\",\"name\":\"Loftel大厦\",\"latitudeLongitude\":\"40.001265,116.462226\",\"typeCode\":\"120200\",\"distance\":0.667823330674415},{\"uid\":\"688\",\"id\":\"120200_688\",\"pictureUrl\":\"http://scloud.toon.mobi/f/TMkRawX-QfGa2vmhNXDRMGZ9hktld-EaUimhZnPQZPsfN_106_106_91_0.jpg\",\"address\":\"金隅丽港城位于朝阳望京花家地西里\",\"name\":\"金隅丽港城\",\"latitudeLongitude\":\"39.990555,116.459498\",\"typeCode\":\"120200\",\"distance\":0.676384216288727},{\"uid\":\"1923\",\"id\":\"120200_1923\",\"pictureUrl\":\"http://scloud.toon.mobi/f/oj0XkX-zfVIcMjP7vP+mNhpztdM13UzieAIEFwqBGOAfN_106_106_91_0.jpg\",\"address\":\"湖光北街9号\",\"name\":\"旺角广场\",\"latitudeLongitude\":\"40.001441,116.46323\",\"typeCode\":\"120200\",\"distance\":0.7368278268459799}]";
    private String schoolDatas = "[{\"uid\":\"156723\",\"latitudeLongitude\":\"39.996401,116.45814\",\"typeCode\":\"141200\",\"id\":\"141200_156723\",\"pictureUrl\":\"http://scloud.toon.mobi/f/sDHEalCII9eklOHMX6osAuzBnRo3O6KVd8djZUzoui0fM_106_106_91_0.jpg\",\"address\":\"朝阳区望京西路思源大厦\",\"name\":\"思源学校\",\"distance\":0.051400494181960625},{\"uid\":\"156598\",\"latitudeLongitude\":\"39.995298,116.459001\",\"typeCode\":\"141200\",\"id\":\"141200_156598\",\"pictureUrl\":\"http://scloud.toon.mobi/f/IQhw-PWJTkbvK1o3PphejUS+yyM2-M+8kNjCwaMVgrUfF_106_106_91_0.png\",\"address\":\"北京市朝阳区南湖中园315号(高中部)\",\"name\":\"北京市第九十四中学\",\"distance\":0.17860033509471532},{\"uid\":\"202981\",\"latitudeLongitude\":\"39.998328,116.456527\",\"typeCode\":\"141200\",\"id\":\"141200_202981\",\"pictureUrl\":\"http://scloud.toon.mobi/f/oQfYlfvBB9kqqjl6EtZFpA9kX7JWVHQyqGAF4h3c+X8fN_106_106_91_0.jpg\",\"address\":\"望京湖光中街8号桔子酒店院内\",\"name\":\"孩子的故事幼儿园\",\"distance\":0.22595077531508248},{\"uid\":\"202641\",\"latitudeLongitude\":\"39.997284,116.460196\",\"typeCode\":\"141200\",\"id\":\"141200_202641\",\"pictureUrl\":\"http://scloud.toon.mobi/f/IvxIYu16WesfcpuxZwMz30jyUhvxIrw3hgb+mBz-cIsfN_106_106_91_0.jpg\",\"address\":\"渠路15号金隅丽港城甲5号楼北侧\",\"name\":\"Kids and Me双语幼稚园\",\"distance\":0.24451770652930302},{\"uid\":\"203332\",\"latitudeLongitude\":\"39.996684,116.460459\",\"typeCode\":\"141200\",\"id\":\"141200_203332\",\"pictureUrl\":\"http://scloud.toon.mobi/f/IQhw-PWJTkbvK1o3PphejUS+yyM2-M+8kNjCwaMVgrUfF_106_106_91_0.png\",\"address\":\"南湖渠西里甲23号\",\"name\":\"启乐思幼儿园\",\"distance\":0.2500116762905769},{\"uid\":\"203315\",\"latitudeLongitude\":\"39.995545,116.460705\",\"typeCode\":\"141200\",\"id\":\"141200_203315\",\"pictureUrl\":\"http://scloud.toon.mobi/f/IQhw-PWJTkbvK1o3PphejUS+yyM2-M+8kNjCwaMVgrUfF_106_106_91_0.png\",\"address\":\"望京街道首开知语城310楼一单元01\",\"name\":\"小神童双语幼儿园\",\"distance\":0.2877797206652723},{\"uid\":\"203329\",\"latitudeLongitude\":\"39.993928,116.456467\",\"typeCode\":\"141200\",\"id\":\"141200_203329\",\"pictureUrl\":\"http://scloud.toon.mobi/f/IQhw-PWJTkbvK1o3PphejUS+yyM2-M+8kNjCwaMVgrUfF_106_106_91_0.png\",\"address\":\"望京南湖西园南路北京香颂238号楼1层\",\"name\":\"IHOMMY MONTESSORI DAYCARE\",\"distance\":0.29495439247102156},{\"uid\":\"202099\",\"latitudeLongitude\":\"39.994301,116.460248\",\"typeCode\":\"141200\",\"id\":\"141200_202099\",\"pictureUrl\":\"http://scloud.toon.mobi/f/I40hn+wSHR6VpOInWE9buxKOZlftDElJOkDEhsF-76QfN_106_106_91_0.jpg\",\"address\":\"南湖南路10号院532号\",\"name\":\"北京师范大学大西洋新城幼儿园\",\"distance\":0.3321387883399333},{\"uid\":\"156143\",\"latitudeLongitude\":\"39.998021,116.461937\",\"typeCode\":\"141200\",\"id\":\"141200_156143\",\"pictureUrl\":\"http://scloud.toon.mobi/f/Jvczd3XNrY+alg1UpgXoB4kmORZhdwBEMW1rM67seY4fN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区南湖中园二区239号楼\",\"name\":\"北京市朝阳区望京新城南湖中园小学\",\"distance\":0.41328665479069465},{\"uid\":\"203017\",\"latitudeLongitude\":\"39.992919,116.455578\",\"typeCode\":\"141200\",\"id\":\"141200_203017\",\"pictureUrl\":\"http://scloud.toon.mobi/f/IQhw-PWJTkbvK1o3PphejUS+yyM2-M+8kNjCwaMVgrUfF_106_106_91_0.png\",\"address\":\"望京南湖中园315号\",\"name\":\"贸易大学对外经贸大学附属中学(北京市第九十四中学)\",\"distance\":0.426723475205728}]";
    private String houseDatas = "[{\"uid\":\"157214\",\"id\":\"120300_157214\",\"pictureUrl\":\"http://scloud.toon.mobi/f/oTTEmi-biIe7mdu6WSJ+JMkhZ+QIrlTu1Uwu1JUhv7AfF_106_106_91_0.png\",\"address\":\"北京市朝阳区望京\",\"name\":\"望京江南府\",\"latitudeLongitude\":\"39.996234,116.457969\",\"typeCode\":\"120300\",\"distance\":0.03942388286817114},{\"uid\":\"6016\",\"id\":\"120300_6016\",\"pictureUrl\":\"http://scloud.toon.mobi/f/sci-0zTi60XABxQz0A3YgMdz6gcVIGuvMUAPtNt229sfN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区望京\",\"name\":\"南湖中园二区\",\"latitudeLongitude\":\"39.996291,116.45705\",\"typeCode\":\"120300\",\"distance\":0.050117205409378494},{\"uid\":\"6658\",\"id\":\"120300_6658\",\"pictureUrl\":\"http://scloud.toon.mobi/f/kvYEPTzg8nxz1tAlPs+Xu5u1FbnvldHSG06ny0NYE7EfN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区望京\",\"name\":\"季景沁园\",\"latitudeLongitude\":\"39.995894,116.455023\",\"typeCode\":\"120300\",\"distance\":0.228113786641948},{\"uid\":\"6035\",\"id\":\"120300_6035\",\"pictureUrl\":\"http://scloud.toon.mobi/f/u8P6OAGQroD96YacvddKLIIuBTEP7s5CXQ34FICX4gwfN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区望京\",\"name\":\"首开知语城\",\"latitudeLongitude\":\"39.99426,116.459154\",\"typeCode\":\"120300\",\"distance\":0.2770347072380303},{\"uid\":\"3857\",\"id\":\"120300_3857\",\"pictureUrl\":\"http://scloud.toon.mobi/f/bh96140j3BkcXvfm+k0iiWbDmthMswECSPYiIbWGrYgfN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区望京\",\"name\":\"银领国际\",\"latitudeLongitude\":\"39.999127,116.456\",\"typeCode\":\"120300\",\"distance\":0.3275243389968151},{\"uid\":\"6249\",\"id\":\"120300_6249\",\"pictureUrl\":\"http://scloud.toon.mobi/f/Lu7sP1TVpTplDFwiXdtRRKyD-QpQ6Dcs5HYN-FvEdgwfN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区望京\",\"name\":\"圣馨大地家园\",\"latitudeLongitude\":\"39.995452,116.462122\",\"typeCode\":\"120300\",\"distance\":0.40076206135247017},{\"uid\":\"5521\",\"id\":\"120300_5521\",\"pictureUrl\":\"http://scloud.toon.mobi/f/6UOSZpfO4wD8SKHWig3ScOf6HVy4s3IbxeiGAB0IwTQfN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区望京\",\"name\":\"世安家园\",\"latitudeLongitude\":\"40.000597,116.45628\",\"typeCode\":\"120300\",\"distance\":0.4747649742148162},{\"uid\":\"424\",\"id\":\"120300_424\",\"pictureUrl\":\"http://scloud.toon.mobi/f/9dG+4H3-bcFhhOUSKvHepOdtwvZbZyzqGJd9G6LWJZYfN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区望京\",\"name\":\"星海明珠\",\"latitudeLongitude\":\"39.993547,116.461892\",\"typeCode\":\"120300\",\"distance\":0.4875900030408864},{\"uid\":\"3268\",\"id\":\"120300_3268\",\"pictureUrl\":\"http://scloud.toon.mobi/f/0joi+58rY1U9OS98jEwnU9TW7PwoZDUED2Ygl+KbpFQfN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区望京\",\"name\":\"南湖渠西里\",\"latitudeLongitude\":\"39.991897,116.455067\",\"typeCode\":\"120300\",\"distance\":0.5502688938201322},{\"uid\":\"6694\",\"id\":\"120300_6694\",\"pictureUrl\":\"http://scloud.toon.mobi/f/jS2DdTTBnrzM2IibUcqTTlznWi0zMOC2qAfrFF-IsysfN_106_106_91_0.jpg\",\"address\":\"北京市朝阳区望京\",\"name\":\"风格雅园\",\"latitudeLongitude\":\"39.994357,116.464026\",\"typeCode\":\"120300\",\"distance\":0.5947033760737184}]";
    private List<NearbyBean> mTaskInfoVos = new LinkedList<>();

    List<NearbyBean> getNearbyBeans() {
        return mTaskInfoVos;
    }

    void setType(int type) {
        this.type = type;
    }

    private String getDatas() {
        String datas;
        switch (type) {
            case EnumConst.OrderType.BUILDING:
                datas = buildingDatas;
                break;
            case EnumConst.OrderType.SCENIC:
                datas = scenicDatas;
                break;
            case EnumConst.OrderType.SCHOOL:
                datas = schoolDatas;
                break;
            case EnumConst.OrderType.HOUSE:
                datas = houseDatas;
                break;
            default:
                datas = estateDatas;
                break;
        }
        return datas;
    }

    Observable<List<NearbyBean>> nearbys(int start, int size) {
        return Observable.create(subscriber -> {
            Observable
                    .timer(1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(aLong -> {
                        if (start == 0) {
                            mTaskInfoVos.clear();
                        }
                        Gson gson = new Gson();
                        Type type = new TypeToken<ArrayList<NearbyBean>>() {
                        }.getType();
                        List<NearbyBean> nearbyBeanList = gson.fromJson(getDatas(), type);
                        mTaskInfoVos.addAll(nearbyBeanList);
                        subscriber.onNext(nearbyBeanList);
                    });
//                TaskApiService.nearbys(type, start, size, new IHttpCallback<List<UserTaskItemModel>>() {
//                    @Override
//                    public void onNext(List<UserTaskItemModel> datas) {
//                        if (start == 0) {
//                            mTaskInfoVos.clear();
//                        }
//                        mTaskInfoVos.addAll(datas);
//                        subscriber.onNext(datas);
//                    }
//
//                    @Override
//                    public void onError(int code, String message) {
//                        subscriber.onError(new Throwable(message));
//                    }
//                });
        });
    }

}
