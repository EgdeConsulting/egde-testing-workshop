#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

FROM mcr.microsoft.com/dotnet/aspnet:6.0 AS base
WORKDIR /app
EXPOSE 5089
EXPOSE 7089

FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src
COPY ["HotelBooking/HotelBooking.csproj", "HotelBooking/"]
RUN dotnet restore "HotelBooking/HotelBooking.csproj"
COPY . .
WORKDIR "/src/HotelBooking"
RUN dotnet build "HotelBooking.csproj" -c Release -o /app/build

FROM build AS publish
RUN dotnet publish "HotelBooking.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "HotelBooking.dll"]
