(ns ice-skating.data
  (:require
   [integrant.core :as ig]
   [next.jdbc :as jdbc]))

(defmethod ig/init-key ::db [_ spec]
  (jdbc/get-datasource spec))

(defmethod ig/init-key ::query-fn [_ {:keys [db]}]
  (fn [query-string]
    (jdbc/execute! db [query-string])))

