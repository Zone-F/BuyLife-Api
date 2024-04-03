// check-in.module.ts
import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { CheckIn, CheckInTask } from '@/entities/checkIn.entity';
import { CheckInService } from './service/check-in.service';
import { CheckInTaskService } from './service/check-in-task.service';

@Module({
  imports: [TypeOrmModule.forFeature([CheckIn, CheckInTask])],
  providers: [CheckInService, CheckInTaskService],
  exports: [CheckInService, CheckInTaskService], // 如果要在其他模块中使用这些服务，请将其导出
})
export class CheckInModule {}
