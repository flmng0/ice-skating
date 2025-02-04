(ns ice-skating.server
  (:require [integrant.core :as ig]
            [ring.adapter.jetty :as jetty]))

(defmethod ig/init-key ::server [_ {:keys [handler] :as opts}]
  (jetty/run-jetty
   handler
   (-> opts (dissoc handler) (assoc :join? false))))

(defmethod ig/halt-key! ::server [_ s]
  (.stop s))


