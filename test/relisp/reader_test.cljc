(ns relisp.reader-test
  (:require [relisp.reader :as reader]
            #?(:clj [clojure.test :as t]
               :cljs [cljs.test :as t :include-macros true])))

(deftest tokenize-test
  (testing "test tokenizer"
    (let [input-string "(def i 1)"
          expected-out '("def" "i" 1)
          actual-out (reader/tokenize input-string)]
      (is (= expected-out actual-out)))))
