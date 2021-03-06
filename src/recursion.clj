(ns recursion)

(defn product [coll]
  (if (empty? coll)
    1
    (* (first coll)
       (product (rest coll)))))

(defn singleton? [coll]
  (and (not (empty? coll)) (empty? (rest coll))))

(defn my-last [coll]
  (if (empty? (rest coll))
    (first coll)
    (my-last (rest coll))))

(defn max-element [a-seq]
  (cond
    (empty? a-seq) nil
    (singleton? a-seq) (first a-seq)
    :else (let [e1 (first a-seq)
                e2 (max-element (rest a-seq))]
            (if (>= e1 e2) e1 e2))))

(defn seq-count [a-seq]
  (if (empty? a-seq)
    0
    (+ 1 (seq-count (rest a-seq)))))

(defn seq-max [seq-1 seq-2]
  (if (> (seq-count seq-1) (seq-count seq-2)) seq-1 seq-2))

(defn longest-sequence [a-seq]
  (cond
    (empty? a-seq) nil
    (singleton? a-seq) (first a-seq)
    :else (seq-max (first a-seq) (longest-sequence (rest a-seq)))))

(defn my-filter [pred? a-seq]
  (cond
    (empty? a-seq) a-seq
    (pred? (first a-seq)) (cons (first a-seq) (my-filter pred? (rest a-seq)))
    :else (my-filter pred? (rest a-seq))))

(defn sequence-contains? [elem a-seq]
  (cond
    (empty? a-seq) false
    (not (= elem (first a-seq))) (sequence-contains? elem (rest a-seq))
    :else true))

(defn my-take-while [pred? a-seq]
  (cond
    (empty? a-seq) a-seq
    (pred? (first a-seq)) (cons (first a-seq) (my-take-while pred? (rest a-seq)))
    :else (empty a-seq)))

(defn my-drop-while [pred? a-seq]
  (cond
    (empty? a-seq) a-seq
    (pred? (first a-seq)) (my-drop-while pred? (rest a-seq))
    :else a-seq))

(defn seq= [a-seq b-seq]
  (cond
    (and (empty? b-seq) (empty? a-seq)) true
    (not (= (seq-count a-seq) (seq-count b-seq))) false
    (not (= (first a-seq) (first b-seq))) false
    :else (seq= (rest a-seq) (rest b-seq))))

(defn my-map [f seq-1 seq-2]
  (if (or (empty? seq-1) (empty? seq-2))
    (empty seq-1)
    (cons (f (first seq-1) (first seq-2)) (my-map f (rest seq-1) (rest seq-2)))))

(defn power [n k]
  (if (zero? k)
    1
    (* n (power n (dec k)))))

(defn fib [n]
  (cond
    (= n 0) 0
    (= n 1) 1
    :else (+ (fib (- n 1)) (fib (- n 2)))))

(defn my-repeat [how-many-times what-to-repeat]
  (if (< how-many-times 1)
    ()
    (cons what-to-repeat (my-repeat (dec how-many-times) what-to-repeat))))

(defn my-range [up-to]
  (if (< up-to 1)
    ()
    (cons (dec up-to) (my-range (dec up-to)))))

(defn keep-n [a-seq n]
  (if (== n (seq-count a-seq))
    a-seq
    (keep-n (rest a-seq) n)))

(defn tails [a-seq]
  (let [f (fn [n] (keep-n a-seq n))]
    (reverse (map f (my-range (inc (seq-count a-seq)))))))

(defn drop-n [a-seq n]
  (reverse (keep-n (reverse a-seq) n)))

(defn inits [a-seq]
  (let [f (fn [n] (drop-n a-seq n))]
    (map f (my-range (inc (seq-count a-seq))))))

(defn rotations [a-seq]
  (if (empty? a-seq)
    [a-seq]
    (my-map concat (rest (tails a-seq)) (rest (inits a-seq)))))

(defn my-frequencies-helper [freqs a-seq]
  (if (empty? a-seq)
    freqs
    (let [elem (first a-seq)
          current-count (or (get freqs elem) 0)
          updated-freqs (assoc freqs elem (inc current-count))]
      (my-frequencies-helper updated-freqs (rest a-seq)))))

(defn my-frequencies [a-seq]
  (my-frequencies-helper {} a-seq))

(defn un-frequencies [a-map]
  (if (empty? a-map)
    a-map
    (let [[elem elem-count] (first a-map)]
      (concat (repeat elem-count elem) (un-frequencies (rest a-map))))))

(defn my-take [n coll]
  (let [my-take-helper
        (fn helper [accum n coll]
          (if (or (empty? coll) (== n (count accum)))
            accum
            (helper (concat accum [(first coll)]) n (rest coll))))]
    (my-take-helper [] n coll)))

(defn my-drop [n coll]
  (let [my-drop-helper
        (fn helper [n-dropped n coll]
          (if (or (empty? coll) (== n n-dropped))
            coll
            (helper (inc n-dropped) n (rest coll))))]
    (my-drop-helper 0 n coll)))

(defn halve [a-seq]
  [:-])

(defn seq-merge [a-seq b-seq]
  [:-])

(defn merge-sort [a-seq]
  [:-])

(defn split-into-monotonics [a-seq]
  [:-])

(defn permutations [a-set]
  [:-])

(defn powerset [a-set]
  [:-])
