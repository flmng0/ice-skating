(ns ice-skating.router
  (:require
   [ice-skating.views :as v]
   [integrant.core :as ig]
   [reitit.ring :as rr]))

; Helper methods
(defn view-handler [id]
  {:get
   (fn [req]
     {:status 200
      :body (v/render-view id req)
      :headers {"Content-Type" "text/html; charset=utf-8"}})})

; Middleware
(defn inject-data [handler data]
  (fn [request]
    (-> request
        (merge data)
        (handler))))

(def routes
  ["/" (view-handler :home)])

(defn make-handler [{:keys [query-fn file-root]}]
  (rr/ring-handler
   (rr/router routes)
   (rr/routes
    (when file-root
      (rr/create-file-handler {:path "/" :root file-root}))
    (rr/create-default-handler))
   {:middleware
    [[inject-data {:query-fn query-fn}]]}))

(defmethod ig/init-key ::handler [_ config]
  (make-handler config))


