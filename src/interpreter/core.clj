(ns interpreter.core
  (:gen-class))

(def vocabulary {:One 1 :Two 2 :Three 3 :Four 4
                 :Five 5 :Six 6 :Seven 7 :Eight 8
                 :Nine 9 :Ten 10 :one 1 :two 2
                 :three 3 :four 4 :five 5 :six 6
                 :seven 7 :eight 8 :nine 9 :ten 10
                 :Plus + :Minus - :Times * :Divide /
                 :plus + :minus - :times * :divide /})


;; Parse a phrase by whitespace
(defn tokenise [sentence]
  (clojure.string/split sentence #" "))



;; Convert strings into keywords which are used as lookup 
;; values in the vocab table
(defn parse [series]
  (map (fn [word] (vocabulary (keyword word)))
       series))



(defn eval [acc series]
  (if (empty? series)
    acc
    (recur ((first series) acc (second series))
          (drop 2 series))))



 ;; Interpret binds vocabulary list to series symbol and combines
 ;; previous functions to lookup values in the vocab table and 
 ;; evaluate an answer

(defn interpret [phrase]
  (let [program (parse (tokenise phrase))]
    (eval (first program) (rest program))))

(defn -main
  [& args]
  (println "Type in two digits between 0 - 10 and a math operation: ")
    (let [response (read-line)]
      (println (interpret response))))