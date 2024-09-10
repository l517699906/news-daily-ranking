(function () {
    "use strict";
    var t = {
        6744: function (t, e, n) {
            var r = n(5100), o = function () {
                var t = this, e = t._self._c;
                return e("div", {attrs: {id: "app"}}, [e("el-row", {attrs: {gutter: 10}}, t._l(t.hotBoards, (function (t, n) {
                    return e("el-col", {key: n, attrs: {span: 6}}, [e("hot-search-board", {
                        attrs: {
                            title: t.title,
                            icon: t.icon,
                            "fetch-url": t.fetchUrl,
                            type: t.type
                        }
                    })], 1)
                })), 1)], 1)
            }, a = [], i = function () {
                var t = this, e = t._self._c;
                return e("el-card", {
                    directives: [{
                        name: "loading",
                        rawName: "v-loading",
                        value: t.loading,
                        expression: "loading"
                    }], staticClass: "custom-card", scopedSlots: t._u([{
                        key: "header", fn: function () {
                            return [e("div", {staticClass: "card-title"}, [e("img", {
                                staticClass: "card-title-icon",
                                attrs: {src: t.icon}
                            }), t._v(" " + t._s(t.title) + "热榜 "), e("span", {staticClass: "update-time"}, [t._v(t._s(t.formattedUpdateTime))])])]
                        }, proxy: !0
                    }])
                }, [e("div", {staticClass: "cell-group-scrollable"}, t._l(t.hotSearchData, (function (n) {
                    return e("div", {
                        key: n.hotSearchOrder,
                        staticClass: "cell-wrapper",
                        class: t.getRankingClass(n.hotSearchOrder)
                    }, [e("span", {staticClass: "cell-order"}, [t._v(t._s(n.hotSearchOrder))]), e("span", {
                        staticClass: "cell-title hover-effect",
                        on: {
                            click: function (e) {
                                return t.openLink(n.hotSearchUrl)
                            }
                        }
                    }, [t._v(" " + t._s(n.hotSearchTitle) + " ")]), e("span", {staticClass: "cell-heat"}, [t._v(t._s(t.formatHeat(n.hotSearchHeat)))])])
                })), 0)])
            }, u = [], l = n(8876);
            const s = l.A.create({
                baseURL: "http://47.121.119.159:80/api",
                headers: {"Content-Type": "application/json"}
            });
            var c = {
                get(t) {
                    return s.get(t)
                }
            }, d = {
                props: {title: String, icon: String, type: String}, data() {
                    return {hotSearchData: [], updateTime: null, loading: !1}
                }, created() {
                    this.fetchData(this.type)
                }, computed: {
                    formattedUpdateTime() {
                        if (!this.updateTime) return "";
                        const t = new Date(this.updateTime), e = new Date, n = e - t, r = Math.floor(n / 1e3 / 60);
                        return r < 1 ? "刚刚更新" : r < 60 ? `${r}分钟前更新` : r < 1440 ? `${Math.floor(r / 60)}小时前更新` : t.toLocaleString()
                    }
                }, methods: {
                    fetchData(t) {
                        this.loading = !0, c.get("/hotSearch/queryByType?type=" + t).then((t => {
                            this.hotSearchData = t.data.data.hotSearchDTOList, this.updateTime = t.data.data.updateTime
                        })).catch((t => {
                            console.error(t)
                        })).finally((() => {
                            this.loading = !1
                        }))
                    }, getRankingClass(t) {
                        return 1 === t ? "top-ranking-1" : 2 === t ? "top-ranking-2" : 3 === t ? "top-ranking-3" : ""
                    }, formatHeat(t) {
                        if ("string" === typeof t && t.endsWith("万")) return t;
                        let e = parseFloat(t);
                        return isNaN(e) ? t : e < 1e3 ? e.toString() : e >= 1e3 && e < 1e4 ? (e / 1e3).toFixed(1) + "k" : e >= 1e4 ? (e / 1e4).toFixed(1) + "万" : void 0
                    }, openLink(t) {
                        t && window.open(t, "_blank")
                    }
                }
            }, f = d, p = n(6367), h = (0, p.A)(f, i, u, !1, null, "abe4f45c", null), g = h.exports, v = {
                name: "App", components: {HotSearchBoard: g}, data() {
                    return {
                        hotBoards: [{title: "百度", icon: n(3378), type: "baidu"}, {
                            title: "抖音",
                            icon: n(683),
                            type: "douyin"
                        }, {title: "知乎", icon: n(9611), type: "zhihu"}, {
                            title: "B站",
                            icon: n(7271),
                            type: "bilibili"
                        }]
                    }
                }
            }, m = v, y = (0, p.A)(m, o, a, !1, null, null, null), b = y.exports, _ = n(6809);
            r["default"].use(_.Calendar), r["default"].use(_.Row), r["default"].use(_.Col), r["default"].use(_.Link), r["default"].use(_.Button), r["default"].use(_.Loading), r["default"].use(_.Container), r["default"].use(_.Header), r["default"].use(_.Footer), r["default"].use(_.Form), r["default"].use(_.Autocomplete), r["default"].use(_.Tooltip), r["default"].use(_.Card), r["default"].use(_.Main), r["default"].use(_.Dialog), r["default"].prototype.$ajax = l.A, r["default"].config.productionTip = !1, new r["default"]({render: t => t(b)}).$mount("#app")
        }, 3378: function (t, e, n) {
            t.exports = n.p + "img/baidu-icon.svg"
        }, 7271: function (t, e, n) {
            t.exports = n.p + "img/bilibili-icon.svg"
        }, 683: function (t, e, n) {
            t.exports = n.p + "img/douyin-icon.svg"
        }, 9611: function (t, e, n) {
            t.exports = n.p + "img/zhihu-icon.svg"
        }
    }, e = {};

    function n(r) {
        var o = e[r];
        if (void 0 !== o) return o.exports;
        var a = e[r] = {id: r, loaded: !1, exports: {}};
        return t[r].call(a.exports, a, a.exports, n), a.loaded = !0, a.exports
    }

    n.m = t, function () {
        n.amdO = {}
    }(), function () {
        var t = [];
        n.O = function (e, r, o, a) {
            if (!r) {
                var i = 1 / 0;
                for (c = 0; c < t.length; c++) {
                    r = t[c][0], o = t[c][1], a = t[c][2];
                    for (var u = !0, l = 0; l < r.length; l++) (!1 & a || i >= a) && Object.keys(n.O).every((function (t) {
                        return n.O[t](r[l])
                    })) ? r.splice(l--, 1) : (u = !1, a < i && (i = a));
                    if (u) {
                        t.splice(c--, 1);
                        var s = o();
                        void 0 !== s && (e = s)
                    }
                }
                return e
            }
            a = a || 0;
            for (var c = t.length; c > 0 && t[c - 1][2] > a; c--) t[c] = t[c - 1];
            t[c] = [r, o, a]
        }
    }(), function () {
        n.n = function (t) {
            var e = t && t.__esModule ? function () {
                return t["default"]
            } : function () {
                return t
            };
            return n.d(e, {a: e}), e
        }
    }(), function () {
        n.d = function (t, e) {
            for (var r in e) n.o(e, r) && !n.o(t, r) && Object.defineProperty(t, r, {enumerable: !0, get: e[r]})
        }
    }(), function () {
        n.g = function () {
            if ("object" === typeof globalThis) return globalThis;
            try {
                return this || new Function("return this")()
            } catch (t) {
                if ("object" === typeof window) return window
            }
        }()
    }(), function () {
        n.o = function (t, e) {
            return Object.prototype.hasOwnProperty.call(t, e)
        }
    }(), function () {
        n.r = function (t) {
            "undefined" !== typeof Symbol && Symbol.toStringTag && Object.defineProperty(t, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(t, "__esModule", {value: !0})
        }
    }(), function () {
        n.nmd = function (t) {
            return t.paths = [], t.children || (t.children = []), t
        }
    }(), function () {
        n.p = "/"
    }(), function () {
        var t = {524: 0};
        n.O.j = function (e) {
            return 0 === t[e]
        };
        var e = function (e, r) {
            var o, a, i = r[0], u = r[1], l = r[2], s = 0;
            if (i.some((function (e) {
                return 0 !== t[e]
            }))) {
                for (o in u) n.o(u, o) && (n.m[o] = u[o]);
                if (l) var c = l(n)
            }
            for (e && e(r); s < i.length; s++) a = i[s], n.o(t, a) && t[a] && t[a][0](), t[a] = 0;
            return n.O(c)
        }, r = self["webpackChunknews_daily_ranking_frontend"] = self["webpackChunknews_daily_ranking_frontend"] || [];
        r.forEach(e.bind(null, 0)), r.push = e.bind(null, r.push.bind(r))
    }();
    var r = n.O(void 0, [504], (function () {
        return n(6744)
    }));
    r = n.O(r)
})();