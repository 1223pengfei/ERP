webpackJsonp([5],{Mbut:function(t,e,a){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var l={props:{userItem:Array,oemList:Array,productList:Array,typeList:Array,nailList:Array,alarmListF:{type:Array},alarmCountF:{type:Number},without:String},data:function(){return{tablewithout:"暂无数据",expands:[],pageSize:10,checkList:[],materialname:"无数据",fuserid:null,dialogTableVisible:!1,pickerOptions:{shortcuts:[{text:"最近一周",onClick:function(t){var e=new Date,a=new Date;a.setTime(a.getTime()-6048e5),t.$emit("pick",[a,e])}},{text:"最近一个月",onClick:function(t){var e=new Date,a=new Date;a.setTime(a.getTime()-2592e6),t.$emit("pick",[a,e])}},{text:"最近三个月",onClick:function(t){var e=new Date,a=new Date;a.setTime(a.getTime()-7776e6),t.$emit("pick",[a,e])}}]},selecttype:[{value:"0",label:"未完成"},{value:"1",label:"已完成"}],allqueList:[],typevalue:null,inputpcode:null,inputValue:null,valueLine:null,statevalue:null,date1:"",date2:"",materialid:"",value:"",que:null,form:{cause:"",solution:"",question:"",quetype:"",remark:""},formLabelWidth:"120px",features:[],currentPage4:4,alarmList:this.alarmListF,alarmCount:this.alarmCountF,serialid:"",oemid:"",oemvalue:null,prov:"",city:"",district:"",currentPage:1,cityArr:[],materialImg:[],materialFile:[],selectList:[],materialFilePDF:[],selectItem:"",districtArr:[]}},watch:{alarmListF:function(t){this.alarmList=t},alarmCountF:function(t){this.alarmCount=t}},methods:{toAlarmExcel:function(){var t=this,e=new URLSearchParams;""==this.selectItem?e.append("select","null"):e.append("select",this.selectItem),e.append("map","null"),this.$http({url:"alarm/download.do",method:"post",data:e,responseType:"blob"}).then(function(e){if("permission.html"===e.data)t.$message.error("您没有权限！请联系管理员！");else{var a=new Blob([e.data],{type:"application/vnd.ms-excel"}),l=document.createElement("a");l.download="问题列表.xls",l.style.display="none",l.href=URL.createObjectURL(a),document.body.appendChild(l),l.click(),URL.revokeObjectURL(l.href),document.body.removeChild(l)}}).catch(function(){t.$message.error("导出失败")})},currentChange:function(t){this.currentPage=t,this.freedomSelect()},sizeChange:function(t){s,this.pageSize=t,this.freedomSelect()},resetting:function(){var t=this;this.date1="",this.date2="",this.statevalue=null,this.valueLine=null,this.inputpcode=null,this.fuserid=null,this.que=null,this.inputValue=null,this.oemvalue=null,this.typevalue=null;var e=new URLSearchParams;e.append("row",this.pageSize),e.append("page",this.currentPage),this.$http.post("/alarm/List.do",e).then(function(e){"permission.html"===e.data?t.tablewithout="无权限，请联系管理员":(t.alarmList=e.data.data,t.alarmCount=e.data.count)})},toogleExpand:function(t){this.dialogTableVisible=!0,this.materialImg=[],this.materailsFileList=[],this.materialFilePDF=[],this.materialFile=[];for(var e=0;e<t.materails.length;e++){if(1==t.materails[e].materialtype){var a=t.materails[e].materialurl;this.materialImg.push(a)}else{var l=t.materails[e],s=t.materails[e].materialurl.lastIndexOf(".");"pdf"==t.materails[e].materialurl.substr(s+1)?this.materialFilePDF.push(l):this.materialFile.push(l)}}},freedomSelect:function(){var t=this,e=this.date1[0];void 0==e&&(e=null);var a=this.date1[1];void 0==a&&(a=null);var l=this.date2[0];void 0==l&&(l=null);var s=this.date2[1];void 0==s&&(s=null);var r="crts&"+e,i="crto&"+a,n="erts&"+l,o="erto&"+s,u="state&"+this.statevalue,c="fzr&"+this.fuserid,p="que&"+this.que,m="tcz&"+this.inputValue,d="oem&"+this.oemvalue,v="num&"+this.inputpcode,h="type&"+this.valueLine,f="pid&"+this.typevalue;this.selectList.push(r,i,n,o,u,c,p,m,d,v,h,f),this.selectItem=this.selectList.join("_");var _=new URLSearchParams;_.append("select",this.selectItem),_.append("row",this.pageSize),_.append("page",this.currentPage),this.selectList=[],this.$http.post("/alarm/List.do",_).then(function(e){"permission.html"===e.data?t.$message.error("您没有权限！请联系管理员！"):(t.alarmList=e.data.data,t.alarmCount=e.data.count)})},getAllAlarm:function(){var t=this,e=new URLSearchParams;e.append("row",this.pageSize),e.append("page",this.currentPage),this.$http.post("/alarm/List.do",e).then(function(e){console.log(e),"permission.html"===e.data?t.tablewithout="无权限，请联系管理员":(t.alarmList=e.data.data,t.alarmCount=e.data.count)})}},mounted:function(){},created:function(){}},r={render:function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("section",{staticClass:"main clearfix"},[a("div",{staticClass:"container-admin"},[a("div",{staticClass:"form-box"},[a("el-form",{ref:"form",attrs:{model:t.form,"label-width":"100px"}},[a("el-row",[a("el-col",{attrs:{span:6}},[a("el-form-item",{staticStyle:{width:"300px"},attrs:{label:"反馈时间"}},[a("el-date-picker",{staticStyle:{width:"220px"},attrs:{type:"daterange",align:"right","unlink-panels":"","value-format":"yyyy-MM-dd","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期","picker-options":t.pickerOptions},model:{value:t.date1,callback:function(e){t.date1=e},expression:"date1"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:5}},[a("el-form-item",{staticStyle:{width:"220px"},attrs:{label:"提出者"}},[a("el-input",{staticClass:"form-item",staticStyle:{width:"200px"},attrs:{placeholder:"请输入提出者姓名"},model:{value:t.inputValue,callback:function(e){t.inputValue=e},expression:"inputValue"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:5}},[a("el-form-item",{staticStyle:{width:"300px"},attrs:{label:"产品线"}},[a("el-select",{staticClass:"form-item",attrs:{clearable:"",placeholder:"根据产线查询"},model:{value:t.valueLine,callback:function(e){t.valueLine=e},expression:"valueLine"}},[a("template",{slot:"empty"},[a("p",[t._v(t._s(t.without))])]),t._v(" "),t._l(t.typeList,function(t){return a("el-option",{key:t.typeid,attrs:{label:t.typename,value:t.typeid}})})],2)],1)],1),t._v(" "),a("el-col",{attrs:{span:5}},[a("el-form-item",{staticStyle:{width:"300px"},attrs:{label:"品名"}},[a("el-select",{staticClass:"form-item",attrs:{clearable:"",placeholder:"请选择产品品名"},model:{value:t.typevalue,callback:function(e){t.typevalue=e},expression:"typevalue"}},[a("template",{slot:"empty"},[a("p",[t._v(t._s(t.without))])]),t._v(" "),t._l(t.productList,function(t){return a("el-option",{key:t.productid,attrs:{label:t.proname,value:t.productid}})})],2)],1)],1)],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:6}},[a("el-form-item",{staticStyle:{width:"300px"},attrs:{label:"完成时间"}},[a("el-date-picker",{staticStyle:{width:"220px"},attrs:{type:"daterange",align:"right","value-format":"yyyy-MM-dd","unlink-panels":"","range-separator":"至","start-placeholder":"开始日期","end-placeholder":"结束日期","picker-options":t.pickerOptions},model:{value:t.date2,callback:function(e){t.date2=e},expression:"date2"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:5}},[a("el-form-item",{staticStyle:{width:"300px"},attrs:{label:"主机厂"}},[a("el-select",{staticClass:"form-item",attrs:{clearable:"",placeholder:"请选择主机厂"},model:{value:t.oemvalue,callback:function(e){t.oemvalue=e},expression:"oemvalue"}},[a("template",{slot:"empty"},[a("p",[t._v(t._s(t.without))])]),t._v(" "),t._l(t.oemList,function(t){return a("el-option",{key:t.oemid,attrs:{label:t.oemname,value:t.oemid}})})],2)],1)],1),t._v(" "),a("el-col",{attrs:{span:5}},[a("el-form-item",{staticStyle:{width:"300px"},attrs:{label:"负责人"}},[a("el-select",{staticClass:"form-item",attrs:{filterable:"",clearable:"",placeholder:"请输入负责人姓名"},model:{value:t.fuserid,callback:function(e){t.fuserid=e},expression:"fuserid"}},[a("template",{slot:"empty"},[a("p",[t._v(t._s(t.without))])]),t._v(" "),t._l(t.userItem,function(t){return a("el-option",{key:t.personid,attrs:{label:t.personname,value:t.personid}})})],2)],1)],1),t._v(" "),a("el-col",{attrs:{span:5}},[a("el-form-item",{staticStyle:{width:"300px"},attrs:{label:"问题点"}},[a("el-select",{staticClass:"div1",attrs:{clearable:"",placeholder:"选择问题"},model:{value:t.que,callback:function(e){t.que=e},expression:"que"}},[a("template",{slot:"empty"},[a("p",[t._v(t._s(t.without))])]),t._v(" "),t._l(t.nailList,function(t){return a("el-option",{key:t.nailid,attrs:{value:t.nailid,label:t.nailname}})})],2)],1)],1)],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:6}},[a("el-form-item",{staticStyle:{width:"320px"},attrs:{label:"状态"}},[a("el-select",{staticClass:"form-item",attrs:{clearable:"",placeholder:"请选择完成状态"},model:{value:t.statevalue,callback:function(e){t.statevalue=e},expression:"statevalue"}},t._l(t.selecttype,function(t){return a("el-option",{key:t.oemid,attrs:{label:t.label,value:t.value}})}),1)],1)],1),t._v(" "),a("el-col",{attrs:{span:12}},[a("el-form-item",{attrs:{label:"序列号"}},[a("el-input",{staticClass:"form-item",staticStyle:{width:"200px"},attrs:{placeholder:"请输入产品序列号"},model:{value:t.inputpcode,callback:function(e){t.inputpcode=e},expression:"inputpcode"}})],1)],1),t._v(" "),a("el-col",{attrs:{span:5}},[a("div",{staticClass:"grid-content bg-purple"},[a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.freedomSelect()}}},[t._v("搜索")]),t._v(" "),a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.resetting()}}},[t._v("重置")])],1)])],1)],1)],1),t._v(" "),a("div",{staticClass:"table"},[a("el-row",[a("el-col",{attrs:{span:2}},[a("h3",{staticClass:"count"},[t._v("总条数："+t._s(t.alarmCount))])]),t._v(" "),a("el-col",{attrs:{span:6}},[a("el-button",{attrs:{type:"text"},on:{click:function(e){t.toAlarmExcel()}}},[t._v("导出问题表格")])],1)],1),t._v(" "),a("el-table",{ref:"multipleTable",staticClass:"table",staticStyle:{width:"100%"},attrs:{data:t.alarmList,border:"","header-cell-class-name":"table-header","empty-text":t.tablewithout}},[a("el-table-column",{attrs:{prop:"creattime",label:"反馈日期"}}),t._v(" "),a("el-table-column",{attrs:{prop:"name",label:"提出者"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.tuser.person.personname))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"typename",label:"产品线"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.serial.remark.product.type.typename))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"oemname",label:"主机厂"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.oem.oemname))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"proname",label:"产品品名"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.serial.remark.product.proname))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"procode",label:"产品编码"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.serial.remark.product.procode))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"serialnumber",label:"产品序列号"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.serial.serialnumber))])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"问题点",prop:"nailname"},scopedSlots:t._u([{key:"default",fn:function(e){return t._l(e.row.nails,function(e,l){return a("div",{key:l},[a("span",[t._v(t._s(e.nailname))])])})}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"name",label:"负责人"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.fuser.person.personname))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"cause",label:"原因分析"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.quest.cause))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"solution",label:"解决方案"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("span",[t._v(t._s(e.row.quest.solution))])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"remark",label:"备注"},scopedSlots:t._u([{key:"default",fn:function(e){return[""==e.row.remark?a("div",[a("span",{staticClass:"counttype"},[t._v("-")])]):a("div",[a("span",{staticClass:"counttype"},[t._v(t._s(e.row.remark))])])]}}])}),t._v(" "),a("el-table-column",{attrs:{prop:"productmodel",label:"完成时间"},scopedSlots:t._u([{key:"default",fn:function(e){return[null==e.row.productmodel?a("div",[a("span",{staticClass:"counttype"},[t._v("-")])]):a("div",[a("span",{staticClass:"counttype"},[t._v(t._s(e.row.productmodel))])])]}}])}),t._v(" "),a("el-table-column",{attrs:{label:"操作"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{type:"text"},on:{click:function(a){t.toogleExpand(e.row)}}},[t._v("查看详情")])]}}])})],1),t._v(" "),a("div",{staticClass:"pageItem"},[a("new-pagination",{attrs:{"page-size":t.pageSize,total:t.alarmCount},on:{"current-change":t.currentChange,"size-change":t.sizeChange}})],1),t._v(" "),a("el-dialog",{attrs:{title:"附件",visible:t.dialogTableVisible,width:"60%"},on:{"update:visible":function(e){t.dialogTableVisible=e}}},[a("el-row",[a("el-col",{attrs:{span:4}},[a("div",{staticClass:"grid-content bg-purple"},[t._v("图片（可预览）：")])]),t._v(" "),a("el-col",{attrs:{span:12}},t._l(t.materialImg,function(t,e){return a("div",{key:e,staticClass:"grid-content bg-purple-light"},[a("a",{attrs:{href:t}},[a("img",{staticClass:"imgItem",attrs:{src:t}})])])}),0)],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:4}},[a("div",{staticClass:"grid-content bg-purple"},[t._v("PDF文件（可预览）：")])]),t._v(" "),a("el-col",{attrs:{span:12}},t._l(t.materialFilePDF,function(e,l){return a("div",{key:l,staticClass:"grid-content bg-purple-light"},[a("a",{attrs:{href:e.materialurl}},[t._v(t._s(e.materialname+"|"))])])}),0)],1),t._v(" "),a("el-row",[a("el-col",{attrs:{span:4}},[a("div",{staticClass:"grid-content bg-purple"},[t._v("其他附件（直接下载）：")])]),t._v(" "),a("el-col",{attrs:{span:12}},t._l(t.materialFile,function(e,l){return a("div",{key:l,staticClass:"grid-content bg-purple-light"},[a("a",{attrs:{href:e.materialurl}},[t._v(t._s(e.materialname+"|"))])])}),0)],1)],1)],1)])])},staticRenderFns:[]};var i=a("VU/8")(l,r,!1,function(t){a("nd3Z")},"data-v-5d72411b",null);e.default=i.exports},nd3Z:function(t,e){}});