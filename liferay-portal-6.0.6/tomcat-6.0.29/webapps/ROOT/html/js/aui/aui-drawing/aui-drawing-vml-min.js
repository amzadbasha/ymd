AUI.add("aui-drawing-vml",function(S){var w=S.Lang,Z=w.isArray,v=w.isNumber,j=w.isObject,L=w.isString,V=w.isUndefined,P=S.config,c=P.doc,b=S.ColorUtil,G=S.Drawing,K=G.Element,f,B=G.Util,m=function(A){return v(A)?A+"px":A;},k=1000+H+1000,T=G.prototype,U=K.prototype,R=G.MAP_ATTRS_AVAILABLE,i={path:1,rect:1},F={M:"m",L:"l",C:"c",Z:"x",m:"t",l:"r",c:"v",z:"x"},N={"-":"shortdash",".":"shortdot","-.":"shortdashdot","-..":"shortdashdotdot",". ":"dot","- ":"dash","--":"longdash","- .":"dashdot","--.":"longdashdot","--..":"longdashdotdot"},W=G.MAP_TYPES_CIRCLE_ELLIPSE,p=Math,o=p.pow,X=p.round,s=G.STR_MS_PROG_ID_PREFIX,h=G.REGEX_ISURL,n=G.REGEX_RADIAL_GRADIENT,u=G.REGEX_SEPARATOR,M=G.REGEX_SEPARATOR_GRADIENT,O=/([clmz]),?([^clmz]*)/gi,C=/-?[^,\s-]+/g,D=/ progid:\S+Blur\([^\)]+\)/g,Q=G.STR_EMPTY,g=G.STR_FILL,H=G.STR_SPACE,E=1.2,J="M{0},{1}l{2},0,0,{3},{4},0z",Y="M{0},{1}l{2},0a{3},{3},0,0,1,{3},{3}l0,{5}a{3},{3},0,0,1,{4},{3}l{6},0a{3},{3},0,0,1,{4},{4}l0,{7}a{3},{3},0,0,1,{3},{4}z",q="position: absolute; left: -9999em; top: -9999em; padding: 0; margin: 0; line-height: 1; display: inline;",d="display: inline-block; position: relative; clip: rect(0 {1} {0} 0); overflow: hidden; height: {0}; width: {1};",t="position: absolute; left: 0; top: 0; height: {0}; width: {1};",l=String.prototype.toLowerCase,a=String.prototype.toUpperCase,I=10;c.createStyleSheet().addRule(".rvml","behavior:url(#default#VML)");try{if(!c.namespaces.rvml){c.namespaces.add("rvml","urn:schemas-microsoft-com:vml");}B.CREATE_ELEMENT=function(A){return c.createElement("<rvml:"+A+' class="rvml">');};}catch(r){B.CREATE_ELEMENT=function(A){return c.createElement("<"+A+' xmlns="urn:schemas-microsoft.com:vml" class="rvml">');};}B.path2vml=function(AF){var AD=/[ahqstv]/ig;var z=B.pathToAbsolute;var AC;if(String(AF).match(AD)){z=B.path2curve;}AD=/[clmz]/g;if(z==B.pathToAbsolute&&!String(AF).match(AD)){AC=String(AF).replace(O,function(AJ,AL,AH){var AK=[];var AG=l.call(AL)=="m";var AI=F[AL];AH.replace(C,function(AM){if(AG&&AK.length==2){AI+=AK+F[AL=="m"?"l":"L"];AK=[];}AK.push(X(AM*I));});return AI+AK;});return AC;}var AE=z(AF);var x;var A;AC=[];for(var AB=0,y=AE.length;AB<y;AB++){x=AE[AB];A=l.call(AE[AB][0]);if(A=="z"){A="x";}for(var AA=1,e=x.length;AA<e;AA++){A+=X(x[AA]*I)+(AA!=e-1?",":Q);}AC.push(A);}return AC.join(H);};B.rectPath=function(A,AB,e,z,AA){if(AA){return S.substitute(Y,[A+AA,AB,e-AA*2,AA,-AA,z-AA*2,AA*2-e,AA*2-z]);}else{return S.substitute(J,[A,AB,e,z,-e]);}};f=G.Impl={addGradientFill:function(z,AD){z.attrs=z.attrs||{};var AE=z.attrs;var AF;var AB="linear";var AC=".5 .5";var x;z.attrs.gradient=AD;AD=String(AD).replace(n,function(AH,AI,AG){AB="radial";if(AI&&AG){AI=parseFloat(AI);AG=parseFloat(AG);if(o(AI-0.5,2)+o(AG-0.5,2)>0.25){AG=p.sqrt(0.25-o(AI-0.5,2))*((AG>0.5)*2-1)+0.5;}AC=AI+H+AG;}return Q;});AD=AD.split(M);if(AB=="linear"){x=AD.shift();x=-parseFloat(x);if(isNaN(x)){return null;}}var AA=B.parseDots(AD);if(!AA){return null;}z=z.shape||z.node;AF=z.getElementsByTagName(g)[0]||B.CREATE_ELEMENT(g);if(!AF.parentNode){z.appendChild(AF);}if(AA.length){AF.on=true;AF.method="none";AF.color=AA[0].color;AF.color2=AA[AA.length-1].color;var A=[];for(var y=0,e=AA.length;y<e;y++){if(AA[y].offset){A.push(AA[y].offset+H+AA[y].color);}}if(AF.colors){AF.colors.value=A.length?A.join():"0% "+AF.color;}if(AB=="radial"){AF.type="gradientradial";AF.focus="100%";AF.focussize=AC;AF.focusposition=AC;}else{AF.type="gradient";AF.angle=(270-x)%360;}}return 1;},clear:function(){var A=this;A.canvas.innerHTML=Q;A.span=c.createElement("span");A.span.style.cssText=q;A.canvas.appendChild(A.span);A.bottom=A.top=null;},createCanvas:function(){var AE=this;var AA=AE.get("contentBox");var AC=AE.get("x");var AB=AE.get("y");var A=m(AE.get("width"));var AF=m(AE.get("height"));var e=c.createElement("div");AE.set("width",1000);AE.set("height",1000);AE.coordsize=I*1000+H+I*1000;AE.coordorigin="0 0";var AD=c.createElement("span");AD.style.cssText=q;e.appendChild(AD);AE.span=AD;AE.canvas=e;var z=e.style;e.style.cssText=S.substitute(d,[AF,A]);AA.prepend(e);},createCircle:function(e,AE,AB){var A=this;var AD=B.CREATE_ELEMENT("group");var AA=B.CREATE_ELEMENT("oval");var z=AA.style;AD.style.cssText=S.substitute(t,[m(A.get("height")),m(A.get("width"))]);AD.coordsize=k;AD.coordorigin=A.coordorigin;AD.appendChild(AA);var AC=new K(AA,AD,A);AC.type="circle";f.setFillAndStroke(AC,{stroke:"#000",fill:"none"});AC.attrs.cx=e;AC.attrs.cy=AE;AC.attrs.r=AB;AC.setBox({x:e-AB,y:AE-AB,width:AB*2,height:AB*2});A.canvas.appendChild(AD);return AC;},createEllipse:function(e,AE,AD,AB){var A=this;var AC=B.CREATE_ELEMENT("group");var AA=B.CREATE_ELEMENT("oval");AC.style.cssText=S.substitute(t,[m(A.get("height")),m(A.get("width"))]);AC.coordsize=k;AC.coordorigin=A.coordorigin;AC.appendChild(AA);var z=new K(AA,AC,A);z.type="ellipse";f.setFillAndStroke(z,{stroke:"#000"});z.attrs.cx=e;z.attrs.cy=AE;z.attrs.rx=AD;z.attrs.ry=AB;z.setBox({x:e-AD,y:AE-AB,width:AD*2,height:AB*2});A.canvas.appendChild(AC);return z;},createImage:function(A,AD,AC,AE,AB){var AG=this;var AF=B.CREATE_ELEMENT("group");var z=B.CREATE_ELEMENT("image");var AA=z.style;AF.style.cssText=S.substitute(t,[m(AG.get("height")),m(AG.get("width"))]);AF.coordsize=k;AF.coordorigin=AG.coordorigin;z.src=A;AF.appendChild(z);var e=new K(z,AF,AG);e.type="image";e.attrs.src=A;e.attrs.x=AD;e.attrs.y=AC;e.attrs.w=AE;e.attrs.h=AB;e.setBox({x:AD,y:AC,width:AE,height:AB});AG.canvas.appendChild(AF);return e;},createPath:function(x){var e=this;var AB=B.CREATE_ELEMENT("group");AB.style.cssText=S.substitute(t,[m(e.get("height")),m(e.get("width"))]);AB.coordsize=e.coordsize;AB.coordorigin=e.coordorigin;var y=B.CREATE_ELEMENT("shape");var z=y.style;z.width=e.get("width")+"px";z.height=e.get("height")+"px";y.coordsize=k;y.coordorigin=e.coordorigin;AB.appendChild(y);var AA=new K(y,AB,e);var A={fill:"none",stroke:"#000"};if(x){A.path=x;}AA.isAbsolute=true;AA.type="path";AA.path=[];AA.Path=Q;f.setFillAndStroke(AA,A);e.canvas.appendChild(AB);return AA;},createRectangle:function(AB,z,AC,e,A){var AE=this;
var AF=B.rectPath(AB,z,AC,e,A);var AA=AE.path(AF);var AD=AA.attrs;AA.X=AD.x=AB;AA.Y=AD.y=z;AA.W=AD.width=AC;AA.H=AD.height=e;AD.r=A;AD.path=AF;AA.type="rect";return AA;},createText:function(AE,AD,AI){var AG=this;var AF=B.CREATE_ELEMENT("group");var AA=B.CREATE_ELEMENT("shape");var AB=AA.style;var AJ=B.CREATE_ELEMENT("path");var A=AJ.style;var AC=B.CREATE_ELEMENT("textpath");var z=m(AG.get("width"));var AH=m(AG.get("height"));AF.style.cssText=S.substitute(t,[AH,z]);AF.coordsize=k;AF.coordorigin=AG.coordorigin;AJ.v=S.substitute("m{0},{1}l{2},{1}",[X(AE*10),X(AD*10),X(AE*10)+1]);AJ.textpathok=true;AB.width=z;AB.height=AH;AC.string=String(AI);AC.on=true;AA.appendChild(AC);AA.appendChild(AJ);AF.appendChild(AA);var e=new K(AC,AF,AG);e.shape=AA;e.textpath=AJ;e.type="text";e.attrs.text=AI;e.attrs.x=AE;e.attrs.y=AD;e.attrs.w=1;e.attrs.h=1;f.setFillAndStroke(e,{font:R.font,stroke:"none",fill:"#000"});e.setBox();AG.canvas.appendChild(AF);return e;},remove:function(){var A=this;A.canvas.parentNode.removeChild(A.canvas);for(var e in A){if(T.hasOwnProperty(e)){A[e]=B.removed(e);}}return true;},setFillAndStroke:function(AE,AN){AE.attrs=AE.attrs||{};var AK=AE.node;var AO=AE.attrs;var AA=AK.style;var AB;var AM=(AN.x!=AO.x||AN.y!=AO.y||AN.width!=AO.width||AN.height!=AO.height||AN.r!=AO.r)&&AE.type=="rect";var AS=AE;for(var AF in AN){if(AN.hasOwnProperty(AF)){AO[AF]=AN[AF];}}if(AM){AO.path=B.rectPath(AO.x,AO.y,AO.width,AO.height,AO.r);AE.X=AO.x;AE.Y=AO.y;AE.W=AO.width;AE.H=AO.height;}if(AN.href){AK.href=AN.href;}if(AN.title){AK.title=AN.title;}if(AN.target){AK.target=AN.target;}if(AN.cursor){AA.cursor=AN.cursor;}if("blur" in AN){AE.blur(AN.blur);}if(AN.path&&AE.type=="path"||AM){AK.path=B.path2vml(AO.path);}if(AN.rotation!=null){AE.rotate(AN.rotation,true);}if(AN.translation){AB=String(AN.translation).split(u);B.translate.call(AE,AB[0],AB[1]);if(AE._.rt.cx!=null){AE._.rt.cx+=+AB[0];AE._.rt.cy+=+AB[1];AE.setBox(AE.attrs,AB[0],AB[1]);}}if(AN.scale){AB=String(AN.scale).split(u);AE.scale(+AB[0]||1,+AB[1]||+AB[0]||1,+AB[2]||null,+AB[3]||null);}if("clip-rect" in AN){var A=String(AN["clip-rect"]).split(u);if(A.length==4){A[2]=+A[2]+(+A[0]);A[3]=+A[3]+(+A[1]);var AG=AK.clipRect||c.createElement("div");var AR=AG.style;var AD=AK.parentNode;AR.clip=S.substitute("rect({1}px {2}px {3}px {0}px)",A);if(!AK.clipRect){AR.position="absolute";AR.top=0;AR.left=0;AR.width=AE.paper.get("width")+"px";AR.height=AE.paper.get("height")+"px";AD.parentNode.insertBefore(AG,AD);AG.appendChild(AD);AK.clipRect=AG;}}if(!AN["clip-rect"]&&AK.clipRect){AK.clipRect.style.clip=Q;}}if(AE.type=="image"&&AN.src){AK.src=AN.src;}if(AE.type=="image"&&AN.opacity){AK.filterOpacity=s+".Alpha(opacity="+(AN.opacity*100)+")";AA.filter=(AK.filterMatrix||Q)+(AK.filterOpacity||Q);}if(AN.font){AA.font=AN.font;}if(AN["font-family"]){AA.fontFamily='"'+AN["font-family"].split(",")[0].replace(/^['"]+|['"]+$/g,Q)+'"';}if(AN["font-size"]){AA.fontSize=AN["font-size"];}if(AN["font-weight"]){AA.fontWeight=AN["font-weight"];}if(AN["font-style"]){AA.fontStyle=AN["font-style"];}if(AN.opacity!=null||AN["stroke-width"]!=null||AN.fill!=null||AN.stroke!=null||AN["stroke-width"]!=null||AN["stroke-opacity"]!=null||AN["fill-opacity"]!=null||AN["stroke-dasharray"]!=null||AN["stroke-miterlimit"]!=null||AN["stroke-linejoin"]!=null||AN["stroke-linecap"]!=null){AK=AE.shape||AK;var AL=(AK.getElementsByTagName(g)&&AK.getElementsByTagName(g)[0]);var AP=false;var x;if(!AL){AP=AL=B.CREATE_ELEMENT(g);}if("fill-opacity" in AN||"opacity" in AN){x=((+AO["fill-opacity"]+1||2)-1)*((+AO.opacity+1||2)-1)*((+b.getRGB(AN.fill).o+1||2)-1);if(x<0){x=0;}else{if(x>1){x=1;}}AL.opacity=x;}if(AN.fill){AL.on=true;}if(AL.on==null||AN.fill=="none"){AL.on=false;}if(AL.on&&AN.fill){var z=AN.fill.match(h);if(z){AL.src=z[1];AL.type="tile";}else{var y=b.getRGB(AN.fill);AL.color=y.hex;AL.src=Q;AL.type="solid";if(y.error&&(AS.type in W||String(AN.fill).charAt(0)!="r")&&f.addGradientFill(AS,AN.fill)){AO.fill="none";AO.gradient=AN.fill;}}}if(AP){AK.appendChild(AL);}var AC=(AK.getElementsByTagName("stroke")&&AK.getElementsByTagName("stroke")[0]);var AQ=false;if(!AC){AQ=AC=B.CREATE_ELEMENT("stroke");}if((AN.stroke&&AN.stroke!="none")||AN["stroke-width"]||AN["stroke-opacity"]!=null||AN["stroke-dasharray"]||AN["stroke-miterlimit"]||AN["stroke-linejoin"]||AN["stroke-linecap"]){AC.on=true;}if(AN.stroke=="none"||AC.on==null||AN.stroke==0||AN["stroke-width"]==0){AC.on=false;}var AJ=b.getRGB(AN.stroke);if(AC.on&&AN.stroke){AC.color=AJ.hex;}x=((+AO["stroke-opacity"]+1||2)-1)*((+AO.opacity+1||2)-1)*((+AJ.o+1||2)-1);var AI=(parseFloat(AN["stroke-width"])||1)*0.75;if(x<0){x=0;}else{if(x>1){x=1;}}if(AN["stroke-width"]==null){AI=AO["stroke-width"];}if(AN["stroke-width"]){AC.weight=AI;}if(AI&&AI<1&&(x*=AI)){AC.weight=1;}AC.opacity=x;if(AN["stroke-linejoin"]){AC.joinstyle=AN["stroke-linejoin"]||"miter";}AC.miterlimit=AN["stroke-miterlimit"]||8;var e=AN["stroke-linecap"];if(e){AC.endcap="round";if(e=="butt"){AC.endcap="flat";}else{if(e=="square"){AC.endcap=e;}}}var AH=AN["stroke-dasharray"];if(AH){AC.dashstyle=Q;if(N.hasOwnProperty(AH)){AC.dashstyle=N[AN["stroke-dasharray"]];}}if(AQ){AK.appendChild(AC);}}if(AS.type=="text"){AA=AS.paper.span.style;if(AO.font){AA.font=AO.font;}if(AO["font-family"]){AA.fontFamily=AO["font-family"];}if(AO["font-size"]){AA.fontSize=AO["font-size"];}if(AO["font-weight"]){AA.fontWeight=AO["font-weight"];}if(AO["font-style"]){AA.fontStyle=AO["font-style"];}if(AS.node.string){AS.paper.span.innerHTML=String(AS.node.string).replace(/</g,"&#60;").replace(/&/g,"&#38;").replace(/\n/g,"<br>");}AS.W=AO.w=AS.paper.span.offsetWidth;AS.H=AO.h=AS.paper.span.offsetHeight;AS.X=AO.x;AS.Y=AO.y+X(AS.H/2);switch(AO["text-anchor"]){case"start":AS.node.style["v-text-align"]="left";AS.bbx=X(AS.W/2);break;case"end":AS.node.style["v-text-align"]="right";AS.bbx=-X(AS.W/2);break;default:AS.node.style["v-text-align"]="center";break;}}}};U.createElement=function(x,y,e){var A=this;A[0]=x;A.id=S.guid();A.node=x;A.X=0;A.Y=0;A.attrs={};A.Group=y;
A.paper=e;A._={tx:0,ty:0,rt:{deg:0},sx:1,sy:1};if(!e.bottom){e.bottom=A;}A.prev=e.top;if(e.top){e.top.next=A;}e.top=A;A.next=null;};U.rotate=function(e,A,x){if(this.removed){return this;}if(e==null){if(this._.rt.cx){return[this._.rt.deg,this._.rt.cx,this._.rt.cy].join(H);}return this._.rt.deg;}e=String(e).split(u);if(e.length-1){A=parseFloat(e[1]);x=parseFloat(e[2]);}e=parseFloat(e[0]);if(A!=null){this._.rt.deg=e;}else{this._.rt.deg+=e;}if(x==null){A=null;}this._.rt.cx=A;this._.rt.cy=x;this.setBox(this.attrs,A,x);this.Group.style.rotation=this._.rt.deg;return this;};U.setBox=function(AA,AB,z){if(this.removed){return this;}var A=this.Group.style;var AC=(this.shape&&this.shape.style)||this.node.style;AA=AA||{};for(var AD in AA){if(AA.hasOwnProperty(AD)){this.attrs[AD]=AA[AD];}}AB=AB||this._.rt.cx;z=z||this._.rt.cy;var AH=this.attrs;var AK;var AJ;var AM;var AF;switch(this.type){case"circle":AK=AH.cx-AH.r;AJ=AH.cy-AH.r;AM=AF=AH.r*2;break;case"ellipse":AK=AH.cx-AH.rx;AJ=AH.cy-AH.ry;AM=AH.rx*2;AF=AH.ry*2;break;case"image":AK=+AH.x;AJ=+AH.y;AM=AH.width||0;AF=AH.height||0;break;case"text":this.textpath.v=["m",X(AH.x),", ",X(AH.y-2),"l",X(AH.x)+1,", ",X(AH.y-2)].join(Q);AK=AH.x-X(this.W/2);AJ=AH.y-this.H/2;AM=this.W;AF=this.H;break;case"rect":case"path":if(!this.attrs.path){AK=0;AJ=0;AM=this.paper.get("width");AF=this.paper.get("height");}else{var AE=B.pathDimensions(this.attrs.path);AK=AE.x;AJ=AE.y;AM=AE.width;AF=AE.height;}break;default:AK=0;AJ=0;AM=this.paper.get("width");AF=this.paper.get("height");break;}AB=(AB==null)?AK+AM/2:AB;z=(z==null)?AJ+AF/2:z;var AG=this.paper.get("width");var AL=this.paper.get("height");var e=AB-AG/2;var AI=z-AL/2;var AN;if(A.left!=(AN=e+"px")){A.left=AN;}if(A.top!=(AN=AI+"px")){A.top=AN;}this.X=i.hasOwnProperty(this.type)?-e:AK;this.Y=i.hasOwnProperty(this.type)?-AI:AJ;this.W=AM;this.H=AF;if(i.hasOwnProperty(this.type)){if(AC.left!=(AN=-e*I+"px")){AC.left=AN;}if(AC.top!=(AN=-AI*I+"px")){AC.top=AN;}}else{if(this.type=="text"){if(AC.left!=(AN=-e+"px")){AC.left=AN;}if(AC.top!=(AN=-AI+"px")){AC.top=AN;}}else{if(A.width!=(AN=AG+"px")){A.width=AN;}if(A.height!=(AN=AL+"px")){A.height=AN;}if(AC.left!=(AN=AK-e+"px")){AC.left=AN;}if(AC.top!=(AN=AJ-AI+"px")){AC.top=AN;}if(AC.width!=(AN=AM+"px")){AC.width=AN;}if(AC.height!=(AN=AF+"px")){AC.height=AN;}}}};U.hide=function(){if(!this.removed){this.Group.style.display="none";}return this;};U.show=function(){if(!this.removed){this.Group.style.display="block";}return this;};U.getRegion=function(){if(this.removed){return this;}if(i.hasOwnProperty(this.type)){return B.pathDimensions(this.attrs.path);}return{x:this.X+(this.bbx||0),y:this.Y,width:this.W,height:this.H};};U.remove=function(){if(this.removed){return;}B.tear(this,this.paper);this.node.parentNode.removeChild(this.node);this.Group.parentNode.removeChild(this.Group);if(this.shape){this.shape.parentNode.removeChild(this.shape);}for(var A in this){delete this[A];}this.removed=true;};U.attr=function(x,AB){var A=this;if(A.removed){return A;}var z;if(x==null){var y={};for(z in A.attrs){if(A.attrs.hasOwnProperty(z)){y[z]=A.attrs[z];}}if(A._.rt.deg){y.rotation=A.rotate();}if(A._.sx!=1||A._.sy!=1){y.scale=A.scale();}if(y.gradient&&y.fill=="none"){y.fill=y.gradient;delete y.gradient;}return y;}if(AB==null&&L(x)){if(x=="translation"){return B.translate.call(A);}if(x=="rotation"){return A.rotate();}if(x=="scale"){return A.scale();}if(x==g&&A.attrs.fill=="none"&&A.attrs.gradient){return A.attrs.gradient;}return A.attrs[x];}if(A.attrs&&AB==null&&Z(x)){var AA;var e={};for(z=0,AA=x.length;z<AA;z++){e[x[z]]=A.attr(x[z]);}return e;}var AC=B.applyAttributes(A,x,AB);if(AC){if(AC.text&&A.type=="text"){A.node.string=AC.text;}f.setFillAndStroke(A,AC);if(AC.gradient&&((W).hasOwnProperty(A.type)||String(AC.gradient).charAt(0)!="r")){f.addGradientFill(A,AC.gradient);}if(!i.hasOwnProperty(A.type)||A._.rt.deg){A.setBox(A.attrs);}}return A;};U.toFront=function(){if(!this.removed){this.Group.parentNode.appendChild(this.Group);}if(this.paper.top!=this){B.toFront(this,this.paper);}return this;};U.toBack=function(){if(this.removed){return this;}if(this.Group.parentNode.firstChild!=this.Group){this.Group.parentNode.insertBefore(this.Group,this.Group.parentNode.firstChild);B.toBack(this,this.paper);}return this;};U.insertAfter=function(A){if(this.removed){return this;}if(A instanceof G.Set){A=A.item(A.size());}if(A.Group.nextSibling){A.Group.parentNode.insertBefore(this.Group,A.Group.nextSibling);}else{A.Group.parentNode.appendChild(this.Group);}B.insertAfter(this,A,this.paper);return this;};U.insertBefore=function(A){if(this.removed){return this;}if(A instanceof G.Set){A=A.item(0);}A.Group.parentNode.insertBefore(this.Group,A.Group);B.insertBefore(this,A,this.paper);return this;};U.blur=function(A){var e=this.node.runtimeStyle;var x=e.filter;x=x.replace(D,Q);if(+A!==0){this.attrs.blur=A;e.filter=x+H+s+".Blur(pixelradius="+(+A||1.5)+")";e.margin=S.substitute("-{0}px 0 0 -{0}px",[X(+A||1.5)]);}else{e.filter=x;e.margin=0;delete this.attrs.blur;}};T._SYNC_UI_ATTRS=T._SYNC_UI_ATTRS.join(",").replace(/,(height|width)/g,"").split(",");T._uiSetWidth=function(y){var x=this;G.superclass._uiSetWidth.apply(x,arguments);var e=x.canvas.style;var A=x.get("height");if(v(y)){y+="px";}if(v(A)){A+="px";}e.width=y;e.clip="rect(0 "+y+" "+A+" 0)";};T._uiSetHeight=function(y){var e=this;G.superclass._uiSetHeight.apply(e,arguments);var A=e.canvas.style;var x=e.get("width");if(v(y)){y+="px";}if(v(x)){x+="px";}A.height=y;A.clip="rect(0 "+x+" "+y+" 0)";};},"1.0.1",{requires:["aui-drawing-base","substitute"]});