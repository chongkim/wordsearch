def s g,t,w,p
  return p if w==""
  [*-1..1].product([*-1..1]).flat_map{|r,c|
    s g,[t[0]+r,t[1]+c],w[1..-1],p+[t]
  }.compact if g[t]&&["?",g[t]].include?(w[0])&&!p.include?(t)
end
a,b=ARGF.read.split(/\n\n/)
g=Hash[*a.split(/\n/).flat_map.with_index{|l,r|
  l.chars.flat_map.with_index{|e,c|[[r,c],e]}
}]
m=b.upcase.split(/,/).map(&:strip).flat_map{|w|
  g.keys.flat_map{|t|s g,t,w,[]}.compact
}
puts [g.group_by{|t,v|t[0]}.sort.map{|e,kvs|
  kvs.map{|t,v|m.include?(t)?"\033[40;m#{v}":"\033[44m#{v}"}.join' '
}.join("\033[0m\n")]
puts "\033[0m",b
