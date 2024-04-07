import { Module } from '@nestjs/common';
import { ScheduleService } from './schedule.service';
import { ScheduleModule as NestScheduleModule } from '@nestjs/schedule';
// import { CheckInModule } from '@/modules/checkIn/check-in.module';
import { CheckIn, CheckInTask } from '@/entities/checkIn.entity';
import { TypeOrmModule } from '@nestjs/typeorm';

@Module({
  imports: [
    NestScheduleModule.forRoot(),
    TypeOrmModule.forFeature([CheckIn, CheckInTask]),
  ],
  providers: [ScheduleService],
})
export class ScheduleModule {}
