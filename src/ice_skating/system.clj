(ns ice-skating.system
  (:require [integrant.core :as ig]
            [ice-skating.server :as server]
            [ice-skating.router :as router]
            [ice-skating.data :as data]))

(def config {::server/server {:port 3000
                              :handler (ig/ref ::router/handler)}

             ::router/handler {:query-fn (ig/ref ::data/query-fn)
                               :resource-root (ig/profile {:dev nil
                                                           :prod "www/build"})}

             ::data/db {:dbtype "sqlite" :dbname "ice-skating.db"}
             ::data/query-fn {:db (ig/ref ::data/db)}})

(defn start-system
  "Start the application with the :prod profile"
  []
  (-> config
      (ig/deprofile [:prod])
      (ig/init)))

(def stop-system ig/halt!)
