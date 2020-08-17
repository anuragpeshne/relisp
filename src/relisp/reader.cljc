(ns relisp.reader)

(defn re-tokens #"[\s,]*(~@|[\[\]{}()'`~^@]|\"(?:[\\].|[^\\\"])*\"?|;.*|[^\s\[\]{}()'\"`@,;]+)")

(defn throw-str [s]
  (throw #?(:cljs (js/Error. s)
            :clj (Exception. s))))

(defn rdr [tokens]
  {:tokens (vec tokens) :position (atom 0)})

(defn rdr-peek [rdr]
  (let [{tokens :tokens, position :position} rdr]
    (get (vec tokens) @position)))

(defn rdr-next [rdr]
  (let [{tokens :tokens, position :position} rdr]
    (swap! position inc)
    (get tokens (dec @position))))

(defn filter-comments [tokens]
  (filter #(not= (first %) \;)))

(defn tokenize [input-string]
  (let [tokens (map second (re-seq re-tokens input-string))]
    (filter-comments tokens)))
