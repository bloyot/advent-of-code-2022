(ns advent-of-code-2022.core
  (:require
   [clojure.string :as str]))

(defn load-input
  "load the input for the day provided and split by lines"
  [day]
  (str/split (slurp (str "resources/input/day" day ".txt")) #"\n"))
