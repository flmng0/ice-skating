(ns ice-skating.router
  (:require
   [ice-skating.views :as v]
   [integrant.core :as ig]
   [reitit.ring :as rr]))

(defn view-handler [id]
  (let [get-handler (fn [req]
                      {:status 200
                       :body (v/render-view id req)
                       :headers {"Content-Type" "text/html"}})]
    {:get get-handler}))

(def routes
  ["/" (view-handler :home)])

(defn inject-data [handler data]
  (fn [request]
    (-> request
        (merge data)
        (handler))))

(defn make-handler [{:keys [query-fn resource-root]}]
  (rr/ring-handler
   (rr/router routes)
   (rr/routes
    (when resource-root
      (rr/create-resource-handler {:path "/" :root resource-root}))
    (rr/create-default-handler))
   {:middleware
    [[inject-data {:query-fn query-fn}]]}))

(defmethod ig/init-key ::handler [_ config]
  (make-handler config))
