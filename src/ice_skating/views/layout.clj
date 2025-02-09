(ns ice-skating.views.layout)

(defn stylesheet [href]
  [:link {:rel "stylesheet" :href href}])

(defn root [{:keys [head title] :or {head [] title "Ice Sequence"}} & content]
  [:html {:lang "en"}
   (into
    [:head]
    (conj
     head
     (stylesheet "/css/root.css")
     [:meta {:name "color-scheme" :content "dark light"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
     [:title title]))
   (into [:body] content)])

(defn default [title & content]
  (root
   {:title title
    :head [[:script {:src "/js/main.js"}]
           (stylesheet "/css/main.css")]}
   [:header
    [:nav.container
     [:ul
      [:li [:strong "Ice Sequence"]]]
     [:ul
      [:li [:a {:href "/"} "Home"]]]]
    (into [:main.container] content)]))

