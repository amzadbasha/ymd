AUI.add("aui-form-select",function(C){var E=C.Lang,F=E.isArray,I=E.isObject,D=C.ClassNameManager.getClassName,J="select",G=D(J),H='<select {multiple} class="{cssClass}" id="{id}" name="{name}"></select>';var B=C.Component.create({NAME:J,ATTRS:{multiple:{value:false},options:{value:[],setter:"_setOptions"},selectedIndex:{value:-1}},UI_ATTRS:["multiple","options","selectedIndex"],EXTENDS:C.Field,HTML_PARSER:{node:"select"},prototype:{FIELD_TEMPLATE:H,FIELD_TYPE:J,_setOptions:function(K){var A=this;if(!F(K)){K=[K];}return K;},_uiSetMultiple:function(K){var A=this;A.get("node").attr("multiple",K);},_uiSetOptions:function(Q){var R=this;var L=[];var N;var O;var P;var K=0;for(var M=0;M<Q.length;M++){N=Q[M];O=N.labelText||N;P=N.value||N;if(N.selected){K=M;}L.push('<option value="'+P+'">'+O+"</option>");}var A=R.get("node");A.all("option").remove(true);A.append(L.join(""));R.set("selectedIndex",K);},_uiSetSelectedIndex:function(K){var A=this;if(K>-1){A.get("node").attr("selectedIndex",K);}}}});C.Select=B;},"1.0.1",{requires:["aui-form-field"]});