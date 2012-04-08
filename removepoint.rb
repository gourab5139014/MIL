File.open('Transfusion.csv','w') do |f|
	c.zip(k).each do |first,second|
		f.puts "#{first},#{second}\n"
	end
end
