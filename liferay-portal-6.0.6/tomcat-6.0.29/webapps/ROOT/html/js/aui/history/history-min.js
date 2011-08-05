/*
Copyright (c) 2010, Yahoo! Inc. All rights reserved.
Code licensed under the BSD License:
http://developer.yahoo.com/yui/license.html
version: 3.2.0
build: nightly
*/
YUI.add("history-base",function(B){var I=B.Lang,E=B.Object,L=YUI.namespace("Env.History"),M=B.Array,N=B.config.doc,F=N.documentMode,J=B.config.win,C={merge:true},H="change",A="add",G="replace";function D(){this._init.apply(this,arguments);}B.augment(D,B.EventTarget,null,null,{emitFacade:true,prefix:"history",preventable:false,queueable:true});if(!L._state){L._state={};}function K(O){return I.type(O)==="object";}D.NAME="historyBase";D.SRC_ADD=A;D.SRC_REPLACE=G;D.html5=!!(J.history&&J.history.pushState&&J.history.replaceState&&("onpopstate" in J||B.UA.gecko>=2));D.nativeHashChange=("onhashchange" in J||"onhashchange" in N)&&(!F||F>7);B.mix(D.prototype,{_init:function(P){var O;P=this._config=P||{};O=this._initialState=this._initialState||P.initialState||null;this.publish(H,{broadcast:2,defaultFn:this._defChangeFn});if(O){this.add(O);}},add:function(){var O=M(arguments,0,true);O.unshift(A);return this._change.apply(this,O);},addValue:function(P,R,O){var Q={};Q[P]=R;return this._change(A,Q,O);},get:function(P){var Q=L._state,O=K(Q);if(P){return O&&E.owns(Q,P)?Q[P]:undefined;}else{return O?B.mix({},Q,true):Q;}},replace:function(){var O=M(arguments,0,true);O.unshift(G);return this._change.apply(this,O);},replaceValue:function(P,R,O){var Q={};Q[P]=R;return this._change(G,Q,O);},_change:function(Q,P,O){O=O?B.merge(C,O):C;if(O.merge&&K(P)&&K(L._state)){P=B.merge(L._state,P);}this._resolveChanges(Q,P,O);return this;},_fireEvents:function(Q,P,O){this.fire(H,{_options:O,changed:P.changed,newVal:P.newState,prevVal:P.prevState,removed:P.removed,src:Q});E.each(P.changed,function(S,R){this._fireChangeEvent(Q,R,S);},this);E.each(P.removed,function(S,R){this._fireRemoveEvent(Q,R,S);},this);},_fireChangeEvent:function(Q,O,P){this.fire(O+"Change",{newVal:P.newVal,prevVal:P.prevVal,src:Q});},_fireRemoveEvent:function(Q,O,P){this.fire(O+"Remove",{prevVal:P,src:Q});},_resolveChanges:function(U,S,P){var T={},O,R=L._state,Q={};if(!S){S={};}if(!P){P={};}if(K(S)&&K(R)){E.each(S,function(V,W){var X=R[W];if(V!==X){T[W]={newVal:V,prevVal:X};O=true;}},this);E.each(R,function(W,V){if(!E.owns(S,V)||S[V]===null){delete S[V];Q[V]=W;O=true;}},this);}else{O=S!==R;}if(O){this._fireEvents(U,{changed:T,newState:S,prevState:R,removed:Q},P);}},_storeState:function(P,O){L._state=O||{};},_defChangeFn:function(O){this._storeState(O.src,O.newVal,O._options);}},true);B.HistoryBase=D;},"3.2.0",{requires:["event-custom-complex"]});YUI.add("history-hash",function(A){var C=A.HistoryBase,F=A.Lang,K=A.Array,J=YUI.namespace("Env.HistoryHash"),B="hash",E,D,I,H=A.config.win,L=H.location,M=A.config.useHistoryHTML5;function G(){G.superclass.constructor.apply(this,arguments);}A.extend(G,C,{_init:function(N){var O=G.parseHash();N=N||{};this._initialState=N.initialState?A.merge(N.initialState,O):O;A.after("hashchange",A.bind(this._afterHashChange,this),H);G.superclass._init.apply(this,arguments);},_storeState:function(P,O){var N=G.createHash(O);G.superclass._storeState.apply(this,arguments);if(G.getHash()!==N){G[P===C.SRC_REPLACE?"replaceHash":"setHash"](N);}},_afterHashChange:function(N){this._resolveChanges(B,G.parseHash(N.newHash),{});}},{NAME:"historyHash",SRC_HASH:B,hashPrefix:"",_REGEX_HASH:/([^\?#&]+)=([^&]+)/g,createHash:function(P){var N=G.encode,O=[];A.Object.each(P,function(R,Q){if(F.isValue(R)){O.push(N(Q)+"="+N(R));}});return O.join("&");},decode:function(N){return decodeURIComponent(N.replace(/\+/g," "));},encode:function(N){return encodeURIComponent(N).replace(/%20/g,"+");},getHash:(A.UA.gecko?function(){var O=/#(.*)$/.exec(L.href),P=O&&O[1]||"",N=G.hashPrefix;return N&&P.indexOf(N)===0?P.replace(N,""):P;}:function(){var O=L.hash.substr(1),N=G.hashPrefix;return N&&O.indexOf(N)===0?O.replace(N,""):O;}),getUrl:function(){return L.href;},parseHash:function(Q){var N=G.decode,R,U,S,O,P={},T=G.hashPrefix,V;Q=F.isValue(Q)?Q:G.getHash();if(T){V=Q.indexOf(T);if(V===0||(V===1&&Q.charAt(0)==="#")){Q=Q.replace(T,"");}}S=Q.match(G._REGEX_HASH)||[];for(R=0,U=S.length;R<U;++R){O=S[R].split("=");P[N(O[0])]=N(O[1]);}return P;},replaceHash:function(N){if(N.charAt(0)==="#"){N=N.substr(1);}L.replace("#"+(G.hashPrefix||"")+N);},setHash:function(N){if(N.charAt(0)==="#"){N=N.substr(1);}L.hash=(G.hashPrefix||"")+N;}});E=J._notifiers;if(!E){E=J._notifiers=[];}A.Event.define("hashchange",{on:function(P,N,O){if(P.compareTo(H)||P.compareTo(A.config.doc.body)){E.push(O);}},detach:function(Q,O,P){var N=K.indexOf(E,P);if(N!==-1){E.splice(N,1);}}});D=G.getHash();I=G.getUrl();if(C.nativeHashChange){A.Event.attach("hashchange",function(P){var N=G.getHash(),O=G.getUrl();K.each(E.concat(),function(Q){Q.fire({_event:P,oldHash:D,oldUrl:I,newHash:N,newUrl:O});});D=N;I=O;},H);}else{if(!J._hashPoll){if(A.UA.webkit&&!A.UA.chrome&&navigator.vendor.indexOf("Apple")!==-1){A.on("unload",function(){},H);}J._hashPoll=A.later(50,null,function(){var N=G.getHash(),O;if(D!==N){O=G.getUrl();K.each(E,function(P){P.fire({oldHash:D,oldUrl:I,newHash:N,newUrl:O});});D=N;I=O;}},null,true);}}A.HistoryHash=G;if(M===false||(!A.History&&M!==true&&(!C.html5||!A.HistoryHTML5))){A.History=G;}},"3.2.0",{requires:["event-synthetic","history-base","yui-later"]});YUI.add("history-hash-ie",function(G){if(G.UA.ie&&!G.HistoryBase.nativeHashChange){var C=G.Do,D=YUI.namespace("Env.HistoryHash"),B=G.HistoryHash,E=D._iframe,F=G.config.win,A=F.location;B.getHash=function(){var H=B.hashPrefix,I=E?E.contentWindow.location.hash.substr(1):A.hash.substr(1);return H&&I.indexOf(H)===0?I.replace(H,""):I;};B.getUrl=function(){var H=B.getHash();if(H&&H!==A.hash.substr(1)){return A.href.replace(/#.*$/,"")+"#"+H;}else{return A.href;}};B._updateIframe=function(I,H){var J=E.contentWindow.document,K=J.location;J.open().close();if(H){K.replace(I.charAt(0)==="#"?I:"#"+I);}else{K.hash=I;}};C.after(B._updateIframe,B,"replaceHash",B,true);C.after(B._updateIframe,B,"setHash");if(!E){G.on("domready",function(){E=D._iframe=G.Node.getDOMNode(G.Node.create('<iframe src="javascript:0" style="display:none" height="0" width="0" tabindex="-1" title="empty"/>'));
G.config.doc.documentElement.appendChild(E);B._updateIframe(A.hash.substr(1));});G.on("hashchange",function(H){if(A.hash.substr(1)!==H.newHash){A.hash=H.newHash;}},F);}}},"3.2.0",{requires:["history-hash","node-base"]});YUI.add("history-html5",function(A){var C=A.HistoryBase,J=A.config.doc,G=A.config.win,I,L=A.config.useHistoryHTML5,K=A.JSON||G.JSON,E="enableSessionFallback",B="YUI_HistoryHTML5_state",D="popstate",F=C.SRC_REPLACE;function H(){H.superclass.constructor.apply(this,arguments);}A.extend(H,C,{_init:function(M){A.on("popstate",this._onPopState,G,this);H.superclass._init.apply(this,arguments);if(M&&M[E]&&YUI.Env.windowLoaded){try{I=G.sessionStorage;}catch(N){}this._loadSessionState();}},_getSessionKey:function(){return B+"_"+G.location.pathname;},_loadSessionState:function(){var M=K&&I&&I[this._getSessionKey()];if(M){try{this._resolveChanges(D,K.parse(M)||null);}catch(N){}}},_storeSessionState:function(M){if(this._config[E]&&K&&I){I[this._getSessionKey()]=K.stringify(M||null);}},_storeState:function(O,N,M){if(O!==D){G.history[O===F?"replaceState":"pushState"](N,M.title||J.title||"",M.url||null);}this._storeSessionState(N);H.superclass._storeState.apply(this,arguments);},_onPopState:function(N){var M=N._event.state;this._storeSessionState(M);this._resolveChanges(D,M||null);}},{NAME:"historyhtml5",SRC_POPSTATE:D});if(!A.Node.DOM_EVENTS.popstate){A.Node.DOM_EVENTS.popstate=1;}A.HistoryHTML5=H;if(L===true||(L!==false&&C.html5)){A.History=H;}},"3.2.0",{optional:["json"],requires:["event-base","history-base","node-base"]});YUI.add("history",function(A){},"3.2.0",{use:["history-base","history-hash","history-hash-ie","history-html5"]});