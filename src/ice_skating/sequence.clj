(ns ice-skating.sequence)

(defn ->move [name entry exit]
  {:name name
   :entry entry
   :exit exit})

(defn edge->int [edge]
  (cond-> 2r000
    (:dominant? edge) (bit-set 0)
    (:forward? edge) (bit-set 1)
    (:inside? edge) (bit-set 2)))

(defn int->edge [desc]
  (cond-> #{}
    (bit-test desc 0) (conj :dominant?)
    (bit-test desc 1) (conj :forward?)
    (bit-test desc 2) (conj :inside?)))

; Currently unused but it's good that it's so simple
#_(defn opposite [edge]
    (-> edge
        (edge->int)
        (bit-flip 1)
        (bit-flip 2)
        (int->edge)))

(defn generate
  "Given a list of moves, generate a cequence that is valid. 
  'Valid' here means moves that can potentially transition into each-other, 
  as one exit edge shares the entry edge of the other move."
  [moveset & {:keys [total] :or {total 10}}]
  (loop [move (rand-nth moveset)
         moves []
         n total]
    (if (> n 0)
      (let [exit (:exit move)
            valid? (fn [move] (= (:entry move) exit))
            valid-moves (filter valid? moveset)
            next-move (rand-nth valid-moves)]
        (recur next-move (conj moves move) (dec n)))
      moves)))

(comment
  (let [moves
        [{:name "A" :entry 2r111 :exit 2r000}
         {:name "B" :entry 2r000 :exit 2r011}
         {:name "C" :entry 2r011 :exit 2r111}
         {:name "D" :entry 2r111 :exit 2r011}
         {:name "E" :entry 2r101 :exit 2r011}
         {:name "F" :entry 2r011 :exit 2r101}]]
    (generate moves :total 5)))

