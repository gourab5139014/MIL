#This file populates the ck.data file which provides values of C,K parameters in CSV
#Modify the following definitions of c and K. THEY SHOULD BE OF EQUAL LENGTH
dataset = ARGV[0]
puts "Writing file #{dataset}.data"
c= []
1.times { c << 8 }
k= []
1.times { k << 3 }

File.open('ck#{dataset}.data','w') do |f|
	c.zip(k).each do |first,second|
		f.puts "#{first},#{second}\n"
	end
end
