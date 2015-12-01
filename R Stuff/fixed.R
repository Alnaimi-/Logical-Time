setwd("Idea Projects/DSSCW2/")
data = read.csv("fixed.csv")

data_mean <- data.frame(threads = integer(0), events = integer(0), comms = integer(0), 
                        pairs = double(0), non.pairs = double(0), ratio = double(0))

for(index in 1:4) {
  seq <- (((index-1)*100)+1):(index*100)
  data_mean[index, ] <- c(5, 100, data$Communications[index*100], 
                          mean(data$Pairs[seq]), mean(data$Non.pairs[seq]), 0)
}

data_mean[5, ] <- c(5, 100, 100, mean(data$Pairs[401:410]), mean(data$Non.pairs[401:410]), 0)

data_mean$ratio <- c((data_mean$pairs/(data_mean$pairs + data_mean$non.pairs)))

plot(data_mean$comms, data_mean$ratio, main="P = 5 and E = 100", 
     ylab="Pair ratio", xlab="No. of communications", pch=19)

abline(lm(data_mean$ratio~data_mean$comms), col="red") # regression line (y~x) 
lines(lowess(data_mean$comms, data_mean$ratio), col="blue") # lowess line (x,y)
