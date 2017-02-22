#File.open("/home/emo/Desktop/wordlist", "r") do |f|
#	File.open("/home/emo/Desktop/new_wordlist2", "wb") do |out|
#  	f.each_line do |line|
#  		out.puts (line.chomp) if line =~ /^[a-z]+\r\n$/
#  	end
#	end
#end

File.open("/home/emo/Desktop/bigbig", "r") do |f|
	File.open("/home/emo/Desktop/bigbig_parsed2", "wb") do |out|
  	f.each_line do |line|
  		line = line.each_char { |char| char.downcase } 
	 		out.puts (line.chomp) if line =~ /^[a-z]+\n$/
  	end
	end
end

