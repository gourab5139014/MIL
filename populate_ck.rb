#This file populates the ck.data file which provides values of C,K parameters in CSV
#Modify the following definitions of c and K. THEY SHOULD BE OF EQUAL LENGTH
c= 1..5
k= [2,3,7,8,9]

File.open('ckHaberman.data','w') do |f|
	c.zip(k).each do |first,second|
		f.puts "#{first},#{second}\n"
	end
end
