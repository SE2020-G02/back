(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4c73d22a"],{"16ef":function(t,o,i){"use strict";i("bce5")},"68b7":function(t,o,i){"use strict";i.r(o);var e=function(){var t=this,o=t.$createElement,e=t._self._c||o;return e("div",{staticClass:"blog"},[e("el-row",{attrs:{gutter:50}},[e("el-col",{attrs:{span:17}},[e("div",{staticClass:"grid-blog bg-blog"},[e("div",{staticClass:"d-flex"},[e("img",{staticStyle:{width:"80px"},attrs:{src:i("a7db")}}),e("div",{staticStyle:{height:"80px"}},[e("div",{staticStyle:{margin:"10px"}},[t._v(t._s(t.author))]),e("div",{staticClass:"fs-sm",staticStyle:{margin:"10px",color:"grey"}},[t._v(" "+t._s(t.signature)+" ")])])]),e("hr"),e("div",{staticClass:"mainbody",staticStyle:{"margin-bottom":"6%"},attrs:{align:"center"}},[e("div",{staticClass:"fs-xl",staticStyle:{margin:"20px"}},[t._v("题目"+t._s(t.problemid))]),e("small-problem",{staticStyle:{width:"25rem",height:"25rem","margin-left":"5%"},attrs:{problemid:t.problemid,isblog:1}}),e("div",{staticClass:"fs-md"},[t._v("当前blog："+t._s(t.blogid))])],1),e("div",{staticStyle:{"margin-left":"10px","margin-bottom":"20px"}},[t._v(" "+t._s(t.blog_info)+" ")]),e("div",{staticStyle:{"margin-left":"10px","margin-bottom":"20px"}},[t._v(" 更新时间："+t._s(t.update_time)+" ")]),e("div",{staticClass:"d-flex",staticStyle:{"margin-left":"10px","margin-bottom":"20px",color:"#0079fe"}},[e("div",{staticStyle:{"margin-left":"auto","margin-right":"5px"},on:{click:function(o){t.visible=!0}}},[t._v(" 编写题解 ")]),e("el-dialog",{attrs:{title:"编写题解",visible:t.visible},on:{"update:visible":function(o){t.visible=o}}},[e("el-form",{ref:"form",attrs:{"label-width":"80px"}},[e("el-form-item",{attrs:{label:"当前题号"}},[t._v(" "+t._s(t.problemid)+" ")]),e("el-form-item",{attrs:{label:"输入题解"}},[e("el-input",{attrs:{type:"textarea",autosize:"ture","show-word-limit":"true",maxlength:"2000"},model:{value:t.newbloginfo,callback:function(o){t.newbloginfo=o},expression:"newbloginfo"}})],1)],1),e("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[e("el-button",{on:{click:function(o){t.visible=!1}}},[t._v("取 消")]),e("el-button",{attrs:{type:"primary"},on:{click:t.submitblog}},[t._v("确 定")])],1)],1)],1)])]),e("el-col",{attrs:{span:7}},[e("div",{staticClass:"grid-blog bg-blog"},[e("div",{staticClass:"fs-xl",staticStyle:{"text-align":"center"}},[t._v("更多解答")]),e("div",{staticStyle:{"margin-top":"10%","margin-bottom":"5%","margin-left":"5px",color:"grey"}},[t._v(" 还有其他"+t._s(t.more_blog_num)+"条解答 ")]),e("el-table",{staticStyle:{width:"100%"},attrs:{data:t.more_blog_list,border:""}},[e("el-table-column",{attrs:{prop:"blogId",label:"Blog"}}),e("el-table-column",{attrs:{prop:"problemId",label:"题目"}}),e("el-table-column",{attrs:{prop:"accountNickname",label:"作者"}}),e("el-table-column",{attrs:{fixed:"right",width:"50"},scopedSlots:t._u([{key:"default",fn:function(o){return[e("el-button",{attrs:{type:"text",size:"small"},on:{click:function(i){return t.readotherblog(o.row.blogId)}}},[t._v("查看")])]}}])})],1)],1)])],1)],1)},l=[],a=i("5364"),n={components:{SmallProblem:a["a"]},data:function(){return{author:"渣男12138",signature:"这个人什么也没说，这个人什么也没说，这个人什么也没说，这个人什么也没说",problemid:12769,blogid:"0000",blog_info:"这里是题解正文，这里是题解正文，这里是题解正文",update_time:"2020-11-17 22:33",more_blog_num:123,more_blog_list:[{name:"王小虎",blogid:1242,problemid:"0003"}],visible:!1,newbloginfo:""}},mounted:function(){this.init()},methods:{init:function(){this.problemid=localStorage.getItem("problemid"),this.getbloginfo(),this.getmorelist()},submitblog:function(){var t=this;this.$axios.post("/blogs/creat",{accountId:localStorage.getItem("userid"),problemId:this.problemid,blogMessage:this.newbloginfo}).then((function(o){0===o.data.code&&(t.blogid=o.data.data.blogId,localStorage.setItem("blogid",o.data.data.blogId),alert("提交成功"),t.init())})).catch((function(t){alert("服务器错误submitblog"),console.log(t)})),this.visible=!1},getbloginfo:function(){var t=this;this.$axios.post("/blogs/display",{problemId:this.problemid,blogId:this.blogid}).then((function(o){1==o.data.code?(t.author="none",t.signature="none",t.blogid="none",t.blog_info="暂无信息：当前还没有人为这道题目编写题解",t.update_time="无"):(t.author=o.data.data.accountPublicInfo.accountNickname,t.signature=o.data.data.accountPublicInfo.accountSignature,t.blogid=o.data.data.blogInfo.blogId,t.blog_info=o.data.data.blogInfo.blogMessage,t.update_time=o.data.data.blogInfo.blogUpdateTime)})).catch((function(t){alert("服务器错误getbloginfo"),console.log(t)}))},getmorelist:function(){var t=this;this.$axios.post("/blogs/all",{problemId:this.problemid}).then((function(o){0===o.data.code&&(t.more_blog_list=o.data.data.blogsInfoList,null==t.more_blog_list?t.more_blog_num=0:t.more_blog_num=t.more_blog_list.length)})).catch((function(t){alert("服务器错误getmorelist"),console.log(t)}))},readotherblog:function(t){this.blogid=t,localStorage.setItem("blogid",t),this.init()},feedback:function(){},write:function(){this.$router.push("writeblog")}}},s=n,r=(i("16ef"),i("2877")),g=Object(r["a"])(s,e,l,!1,null,null,null);o["default"]=g.exports},a7db:function(t,o){t.exports="data:image/png;base64,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"},bce5:function(t,o,i){}}]);
//# sourceMappingURL=chunk-4c73d22a.d84372f5.js.map