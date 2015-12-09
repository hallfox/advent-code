(ns advent-code.core
  (:gen-class))

;; 'a' char
;; \a clojure char
(defn santa-moves [moves]
  "Takes a string and turns it into a list of integers"
  (map (fn [x] (if (= \( x)
                 1
                 -1))
       moves))

;; Open parenthesis '(' -> +1 floor
;; Close paren ')' -> -1 floor
(defn day1-1 []
    (reduce + (santa-moves (read-line))))

;; Python
;; my_list = [1,2,3,4]
;; my_list[0] = 102 # [102, 2, 3, 4]
;; NOT ALLOWED
;; "())" -> 3
;; map fn seq: (map inc [1 2 3]) -> [2 3 4]
(defn day1-2 []
  "Finds the first index where Santa goes to the basement"
  (nth (reduce (fn [[current-floor current-index] step]
                  ;; Either the index is about to become negative or
                  ;; we can still keep going
                  (let [next-floor (+ current-floor step)] ;; next_floor = current_floor + step
                    (if (neg? next-floor) ;; check if next floor is in the basement
                      (reduced [next-floor current-index]) ;; eval to this if true
                      [next-floor (inc current-index)]))) ;; eval to this if false
                [0 1] ;; Accumulator: [starting-floor index]
                (santa-moves (read-line))) ;; Seq to reduce on

  1))
(defn solve-day
  "switch board for functions that solve each challenge"
  [day]
  (case day
    "1-1" (day1-1)
    "1-2" (day1-2)
    "No matching challenge found"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [day (first args)
         result (solve-day day)]
     (println result)))
